package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.components.WebClientTemplate;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenInterface;
import net.thewesthill.wps.service.Oauth2TokenParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StandaloneAccessTokenClient implements AccessTokenInterface {

    private final ClientCredentialsProperties properties;

    private final WebClientTemplate webClientTemplate;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getWpsTokenAsync(Oauth2TokenParam oauth2TokenRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {{
            add("grant_type", oauth2TokenRequest.getGrantTypes().getInfo());
            add("client_id", properties.getClientId());
            add("client_secret", properties.getClientSecret());
        }};

        return webClientTemplate.postWithResponseEntityAsync(
                UrlConstants.WPS_TOKEN_URL, MediaType.APPLICATION_FORM_URLENCODED,
                params, null, webClientTemplate.getMapTypeReference()
        );
    }

    @Override
    public ResponseEntity<Map<String, Object>> getWpsTokenSync(Oauth2TokenParam oauth2TokenRequest) {
        return webClientTemplate.syncExecute(getWpsTokenAsync(oauth2TokenRequest));
    }

}
