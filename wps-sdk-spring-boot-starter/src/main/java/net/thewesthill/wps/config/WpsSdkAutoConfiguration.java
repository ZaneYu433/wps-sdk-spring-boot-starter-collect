package net.thewesthill.wps.config;

import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.impl.StandaloneClientTokenBuilder;
import net.thewesthill.wps.service.impl.UserClientTokenBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnClass(WebClient.class)
@Import(WebClientConfiguration.class)
@EnableConfigurationProperties(value = ClientCredentialsProperties.class)
public class WpsSdkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserClientTokenBuilder getUserClientTokenBuilder()
    {
        return new UserClientTokenBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    public StandaloneClientTokenBuilder getStandaloneClientTokenBuilder()
    {
        return new StandaloneClientTokenBuilder();
    }
}
