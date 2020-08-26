package es.brujula.searcher.infrastructure.filter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtProperties jwtProperties;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
        super(authenticationManager);
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null == authentication) {
            filterChain.doFilter(request, response);
            return;
        }

        response.addHeader("Access-Control-Expose-Headers", "Authorization");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(JwtProperties.TOKEN_HEADER);
        if (StringUtils.isEmpty(token) || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return null;
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;

        Jws<Claims> parsedToken = this.obtainToken(token);

        String username = this.obtainUsername(parsedToken);

        List<SimpleGrantedAuthority> authorities = this.obtainRoles(parsedToken);

        if (StringUtils.isNotEmpty(username)) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }

        return authenticationToken;
    }

    private String obtainUsername(Jws<Claims> parsedToken) {
        return parsedToken
                .getBody()
                .getSubject();
    }

    private List<SimpleGrantedAuthority> obtainRoles(Jws<Claims> parsedToken) {

        return ((List<?>) parsedToken.getBody()
                .get("rol")).stream()
                .map(authority -> new SimpleGrantedAuthority((String) authority))
                .collect(Collectors.toList());
    }

    private Jws<Claims> obtainToken(String token) {

        byte[] signingKey = this.jwtProperties.secret().getBytes();

        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();

        String tokenValue = token.replace(JwtProperties.TOKEN_PREFIX, "");

        return jwtParser
                .parseClaimsJws(tokenValue);
    }
}
