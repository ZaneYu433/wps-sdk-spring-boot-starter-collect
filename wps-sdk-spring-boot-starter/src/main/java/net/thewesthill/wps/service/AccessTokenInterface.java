package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import net.thewesthill.wps.model.oauth2.token.request.Oauth2TokenParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
public interface AccessTokenInterface<T> {
    Mono<ResponseEntity<T>> getWpsTokenAsync(@Valid Oauth2TokenParam oauth2TokenRequest);

    ResponseEntity<T> getWpsTokenSync(@Valid Oauth2TokenParam oauth2TokenRequest);
}
