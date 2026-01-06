package net.thewesthill.wps.config;

import net.thewesthill.wps.service.impl.StandaloneClientTokenBuilder;
import net.thewesthill.wps.service.impl.UserClientTokenBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(value = ClientCredentialsProperties.class)
@Import(RestTemplateConfig.class)
public class WpsSdkAutoConfiguration {

    @Bean
    public UserClientTokenBuilder getUserClientTokenBuilder(ClientCredentialsProperties properties)
    {
        return new UserClientTokenBuilder();
    }

    @Bean
    public StandaloneClientTokenBuilder getStandaloneClientTokenBuilder(ClientCredentialsProperties properties)
    {
        return new StandaloneClientTokenBuilder();
    }
}
