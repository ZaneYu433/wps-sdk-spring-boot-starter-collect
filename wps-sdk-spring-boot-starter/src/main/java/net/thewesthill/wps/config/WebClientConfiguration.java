package net.thewesthill.wps.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@ConditionalOnClass(WebClient.class)
public class WebClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient()
    {
        return WebClient.builder()
                .baseUrl("https://openapi.wps.cn")
                .build();
    }
}
