package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenBuilder;
import net.thewesthill.wps.service.Oauth2TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClientTokenBuilder implements AccessTokenBuilder {

    @Autowired
    private ClientCredentialsProperties properties;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<String> getWpsTokenAsync(Oauth2TokenRequest oauth2TokenRequest) {
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
                .bodyToMono(String.class);
    }

    @Override
    public String getWpsTokenSync(Oauth2TokenRequest oauth2TokenRequest) {
        return getWpsTokenAsync(oauth2TokenRequest).block();
    }

    public String AuthSender(String redirectUri, String scope, String state) {
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
}
