package net.thewesthill.wps.configs;

import net.thewesthill.wps.components.WebClientTemplate;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.UserClient;
import net.thewesthill.wps.service.impl.CloudDocClient;
import net.thewesthill.wps.service.impl.StandaloneAccessTokenClient;
import net.thewesthill.wps.service.impl.UserAccessTokenClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@ComponentScan("net.thewesthill.wps")
@Configuration
@ConditionalOnClass(WebClient.class)
@Import({WebClientConfiguration.class, WebClientTemplate.class})
@EnableConfigurationProperties(value = ClientCredentialsProperties.class)
public class WpsSdkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserAccessTokenClient getUserClientTokenBuilder(ClientCredentialsProperties properties, WebClientTemplate template) {
        return new UserAccessTokenClient(properties, template);
    }

    @Bean
    @ConditionalOnMissingBean
    public StandaloneAccessTokenClient getStandaloneClientTokenBuilder(ClientCredentialsProperties properties, WebClientTemplate template) {
        return new StandaloneAccessTokenClient(properties, template);
    }

    @Bean
    @ConditionalOnMissingBean
    public CloudDocClient getCloudDocClient(WebClientTemplate template) {
        return new CloudDocClient(template);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserClient getUserClient(ClientCredentialsProperties properties, WebClientTemplate template) {
        return new UserClient(properties, template);
    }

}
