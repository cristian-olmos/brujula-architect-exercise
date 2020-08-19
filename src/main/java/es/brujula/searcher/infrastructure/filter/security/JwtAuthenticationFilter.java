package es.brujula.searcher.infrastructure.filter.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        this.authenticationManager = authenticationManager;
        this.jwtProperties = jwtProperties;

        this.setupAuthenticateUrl();
    }

    private void setupAuthenticateUrl() {
        setFilterProcessesUrl(this.jwtProperties.authUrl());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        List<String> roles = this.rolesFrom(user);

        String token = generateToken(user, roles);

        response.addHeader(JwtProperties.TOKEN_HEADER, JwtProperties.TOKEN_PREFIX + token);
    }

    private List<String> rolesFrom(User user) {
        return user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private String generateToken(User user, List<String> roles) {

        byte[] signingKey = this.jwtProperties.secret().getBytes();

        Date date = this.obtainExpirationDateInMilliseconds();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", JwtProperties.TOKEN_TYPE)
                .setIssuer(JwtProperties.TOKEN_ISSUER)
                .setAudience(JwtProperties.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(date)
                .claim("rol", roles)
                .compact();
    }

    private Date obtainExpirationDateInMilliseconds() {
        return new Date(System.currentTimeMillis() + this.jwtProperties.expirationTimeInMilliseconds());
    }
}
