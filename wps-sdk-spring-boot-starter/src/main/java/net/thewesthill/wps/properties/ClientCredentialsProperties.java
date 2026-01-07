package net.thewesthill.wps.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wps-sdk")
public class ClientCredentialsProperties {

    /**
     * 应用 APP ID
     */
    private String clientId;

    /**
     * 应用 APP KEY
     */
    private String clientSecret;

    public ClientCredentialsProperties() {}

    public ClientCredentialsProperties(String clientId, String clientSecret)
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
