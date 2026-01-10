package net.thewesthill.wps.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
