package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.config.ClientCredentialsProperties;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.service.AccessTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class StandaloneClientTokenBuilder implements AccessTokenBuilder {

    @Autowired
    private ClientCredentialsProperties properties;

    @Autowired
    private RestTemplate restTemplate;

    public String getWpsToken(String grantType) {
        try {
            HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", grantType);
            params.add("client_id", properties.getClientId());
            params.add("client_secret", properties.getClientSecret());

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(UrlConstants.WPS_TOKEN_URL, requestEntity, String.class);
            return response.getBody();
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
