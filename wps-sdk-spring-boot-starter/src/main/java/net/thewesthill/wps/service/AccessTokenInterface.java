package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Map;

@Validated
public interface AccessTokenInterface {

    Mono<ResponseEntity<Map<String, Object>>> getWpsTokenAsync(@Valid Oauth2TokenParam oauth2TokenRequest);

    ResponseEntity<Map<String, Object>> getWpsTokenSync(@Valid Oauth2TokenParam oauth2TokenRequest);
}
