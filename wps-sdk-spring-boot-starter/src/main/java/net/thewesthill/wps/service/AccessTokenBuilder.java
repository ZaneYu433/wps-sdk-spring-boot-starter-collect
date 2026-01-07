package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface AccessTokenBuilder {
    Mono<String> getWpsTokenAsync(@Valid Oauth2TokenRequest oauth2TokenRequest);

    String getWpsTokenSync(@Valid Oauth2TokenRequest oauth2TokenRequest);
}
