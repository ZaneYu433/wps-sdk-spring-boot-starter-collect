package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.config.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserClientTokenBuilder implements AccessTokenBuilder {

    @Autowired
    private ClientCredentialsProperties properties;

    @Autowired
    private RestTemplate restTemplate ;

    public String getWpsToken(String grantType) {
//        try {
//            HttpHeaders headers = new org.springframework.http.HttpHeaders();
//            headers.setContentType(MediaType.);
//        }
//        catch (Exception ex) {
//
//        }
        return "";
    }
}
