package es.brujula.searcher.infrastructure.filter.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private String secret;
    private String authUrl;
    private Long expirationTimeInMilliseconds;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public Long getExpirationTimeInMilliseconds() {
        return expirationTimeInMilliseconds;
    }

    public void setExpirationTimeInMilliseconds(Long expirationTimeInMilliseconds) {
        this.expirationTimeInMilliseconds = expirationTimeInMilliseconds;
    }

    public String secret() {
        return this.secret;
    }

    public String authUrl() {
        return this.authUrl;
    }

    public Long expirationTimeInMilliseconds() {
        return this.expirationTimeInMilliseconds;
    }
}
