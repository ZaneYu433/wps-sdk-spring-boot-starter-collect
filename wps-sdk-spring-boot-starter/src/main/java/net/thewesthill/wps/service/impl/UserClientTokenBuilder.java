package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenBuilder;
import net.thewesthill.wps.service.Oauth2TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class UserClientTokenBuilder implements AccessTokenBuilder {

    @Autowired
    private ClientCredentialsProperties properties;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Map<String, Object>> getWpsTokenAsync(Oauth2TokenRequest oauth2TokenRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
            add("client_id", properties.getClientId());
            add("client_secret", properties.getClientSecret());
            add("code", oauth2TokenRequest.getCode());
            add("redirect_uri", oauth2TokenRequest.getRedirectUri());
        }};

        return webClient.post()
                .uri(UrlConstants.WPS_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(params)
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Request Error: " + response.statusCode()))
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    @Override
    public Map<String, Object> getWpsTokenSync(Oauth2TokenRequest oauth2TokenRequest) {
        return getWpsTokenAsync(oauth2TokenRequest).block();
    }

    public String authSender(String redirectUri, String scope, String state) {
        ClientResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(UrlConstants.OAUTH2_AUTH_URL)
                        .queryParam("client_id", properties.getClientId())
                        .queryParam("response_type", "code")
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", scope)
                        .queryParam("state", state)
                        .build())
                .exchangeToMono(Mono::just)
                .block();

        if (response == null || response.statusCode() != HttpStatus.FOUND) {
            throw new RuntimeException("Request Error: " + (response != null ? response.statusCode() : "Empty Response"));
        }

        return response.headers().header("Location")
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Response Not Contain RedirectUrl"));
    }

    public Mono<Map<String, Object>> refreshTokenAsync(Oauth2TokenRequest oauth2TokenRequest)
    {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
            add("refresh_token", oauth2TokenRequest.getRefreshToken());
            add("client_id", properties.getClientId());
            add("client_secret", properties.getClientSecret());
        }};

        return webClient.post()
                .uri(UrlConstants.WPS_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(params)
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Request Error: " + response.statusCode()))
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    public Map<String, Object> refreshTokenSync(Oauth2TokenRequest oauth2TokenRequest)
    {
        return refreshTokenAsync(oauth2TokenRequest).block();
    }
}
