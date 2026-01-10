package net.thewesthill.wps.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnClass(WebClient.class)
public class WebClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://openapi.wps.cn")
                .build();
    }
}

