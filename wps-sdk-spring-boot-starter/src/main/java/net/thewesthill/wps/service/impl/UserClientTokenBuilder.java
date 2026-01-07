package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.properties.ClientCredentialsProperties;
import net.thewesthill.wps.service.AccessTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientTokenBuilder implements AccessTokenBuilder {

    @Autowired
    private ClientCredentialsProperties properties;

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
