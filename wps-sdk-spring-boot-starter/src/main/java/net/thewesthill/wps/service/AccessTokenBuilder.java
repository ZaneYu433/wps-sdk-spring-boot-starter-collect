package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AccessTokenBuilder {
    Mono<Map<String, Object>> getWpsTokenAsync(@Valid Oauth2TokenRequest oauth2TokenRequest);

    Map<String, Object> getWpsTokenSync(@Valid Oauth2TokenRequest oauth2TokenRequest);
}
