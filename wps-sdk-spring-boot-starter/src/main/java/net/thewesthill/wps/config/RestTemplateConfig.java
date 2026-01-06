package net.thewesthill.wps.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnClass(RestTemplate.class)
public class RestTemplateConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
