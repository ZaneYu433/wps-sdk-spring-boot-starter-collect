package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenInterface;
import net.thewesthill.wps.service.Oauth2TokenParam;
import net.thewesthill.wps.utils.WebClientTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAccessTokenClient implements AccessTokenInterface {

    private final ClientCredentialsProperties properties;

    private final WebClientTemplate webClientTemplate;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getWpsTokenAsync(Oauth2TokenParam oauth2TokenRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
            add("client_id", properties.getClientId());
            add("client_secret", properties.getClientSecret());
            add("code", oauth2TokenRequest.getCode());
            add("redirect_uri", oauth2TokenRequest.getRedirectUri());
        }};

        return webClientTemplate.postFormWithResponseEntityAsync(UrlConstants.WPS_TOKEN_URL, params, null, webClientTemplate.getMapTypeReference());
    }

    @Override
    public ResponseEntity<Map<String, Object>> getWpsTokenSync(Oauth2TokenParam oauth2TokenRequest) {
        return webClientTemplate.syncExecute(getWpsTokenAsync(oauth2TokenRequest));
    }

    public String authSender(String redirectUri, String scope, String state) {

        ClientResponse response = webClientTemplate.syncExecute(webClientTemplate.GetClientResponse(UrlConstants.OAUTH2_AUTH_URL, new LinkedMultiValueMap<>() {
            {
                add("client_id", properties.getClientId());
                add("response_type", "code");
                add("redirect_uri", redirectUri);
                add("scope", scope);
                add("state", state);
            }
        }));

        if (response == null || response.statusCode() != HttpStatus.FOUND) {
            throw new RuntimeException("Request Error: " + (response != null ? response.statusCode() : "Empty Response"));
        }

        return response.headers().header("Location").stream().findFirst().orElseThrow(() -> new RuntimeException("Response Not Contain RedirectUrl"));
    }

    public Mono<ResponseEntity<Map<String, Object>>> refreshTokenAsync(Oauth2TokenParam oauth2TokenRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
            add("refresh_token", oauth2TokenRequest.getRefreshToken());
            add("client_id", properties.getClientId());
            add("client_secret", properties.getClientSecret());
        }};

        return webClientTemplate.postFormWithResponseEntityAsync(UrlConstants.WPS_TOKEN_URL, params, null, webClientTemplate.getMapTypeReference());
    }

    public ResponseEntity<Map<String, Object>> refreshTokenSync(Oauth2TokenParam oauth2TokenRequest) {
        return webClientTemplate.syncExecute(refreshTokenAsync(oauth2TokenRequest));
    }

}
