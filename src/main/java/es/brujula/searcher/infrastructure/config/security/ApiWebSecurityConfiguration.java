package es.brujula.searcher.infrastructure.config.security;

import es.brujula.searcher.infrastructure.filter.security.JwtAuthenticationFilter;
import es.brujula.searcher.infrastructure.filter.security.JwtAuthorizationFilter;
import es.brujula.searcher.infrastructure.filter.security.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(JwtProperties.class)
public class ApiWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    public ApiWebSecurityConfiguration(PasswordEncoder passwordEncoder, JwtProperties jwtProperties) {
        this.passwordEncoder = passwordEncoder;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String authUrl = jwtProperties.authUrl().concat("*");

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(authUrl).permitAll()
                .antMatchers("/v1/services").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger.json").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), this.jwtProperties))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.jwtProperties))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test")
                .password(passwordEncoder.encode("12345"))
                .roles("USER");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
