package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.model.oauth2.token.request.Oauth2TokenParam;
import net.thewesthill.wps.model.oauth2.token.response.StandaloneTokenClientResponse;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StandaloneAccessTokenClient implements AccessTokenInterface<StandaloneTokenClientResponse> {

    private final ClientCredentialsProperties properties;

    private final WebClient webClient;

    @Override
    public Mono<ResponseEntity<StandaloneTokenClientResponse>> getWpsTokenAsync(Oauth2TokenParam oauth2TokenRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
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
                .bodyToMono(StandaloneTokenClientResponse.class)
                .map(ResponseEntity::ok);
    }

    @Override
    public ResponseEntity<StandaloneTokenClientResponse> getWpsTokenSync(Oauth2TokenParam oauth2TokenRequest) {
        return getWpsTokenAsync(oauth2TokenRequest).block();
    }
}
