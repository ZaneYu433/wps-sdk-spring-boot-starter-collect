package net.thewesthill.wps.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.WpsApiException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientTemplate {

    private final WebClient webClient;

    public Mono<? extends Throwable> handleNon2xxResponse(ClientResponse response) {
        return Mono.error(new WpsApiException("WebClient Request Failed, Status Code: " + response.statusCode()));
    }

    public Mono<ResponseEntity<Map<String, Object>>> handleHeaderResponse(String message) {
        return Mono.error(new WpsApiException(message));
    }

    public <T> Mono<T> postAsync(String uri, MediaType mediaType, Object formParams, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return webClient.post().uri(uri).contentType(mediaType).bodyValue(formParams).headers(httpHeaders -> {
            if (headers != null) {
                httpHeaders.addAll(headers);
            }
        }).retrieve().onStatus(status -> !status.is2xxSuccessful(), this::handleNon2xxResponse).bodyToMono(responseType);
    }

    public <T> Mono<ResponseEntity<T>> postWithResponseEntityAsync(String uri, MediaType mediaType, Object formParams, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return this.postAsync(uri, mediaType, formParams, headers, responseType).map(ResponseEntity::ok);
    }

    private WebClient.RequestHeadersSpec<?> buildGetSpec(String uri, MultiValueMap<String, Object> params) {
        return webClient.get().uri(uriBuilder -> {
            UriBuilder b1 = uriBuilder.path(uri);
            params.forEach((k, v) -> b1.queryParamIfPresent(k, Optional.of(v)));
            System.out.println(b1.build());
            return b1.build();
        });
    }

    public Mono<ClientResponse> getClientResponse(String uri, MultiValueMap<String, Object> params) {
        return buildGetSpec(uri, params).exchangeToMono(Mono::just);
    }

    public <T> Mono<T> getAsync(String uri, MultiValueMap<String, Object> params, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return buildGetSpec(uri, params).headers(httpHeaders -> {
            if (headers != null) {
                httpHeaders.addAll(headers);
            }
        }).retrieve().onStatus(status -> !status.is2xxSuccessful(), this::handleNon2xxResponse).bodyToMono(responseType);
    }

    public <T> Mono<ResponseEntity<T>> getWithResponseEntityAsync(String uri, MultiValueMap<String, Object> params, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return this.getAsync(uri, params, headers, responseType).map(ResponseEntity::ok);
    }

    public <T> Mono<T> putAsync(String uri, MediaType mediaType, Object params, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return webClient.put().uri(uri).contentType(mediaType).bodyValue(params).headers(httpHeaders -> {
            if (headers != null) {
                httpHeaders.addAll(headers);
            }
        }).retrieve().onStatus(status -> !status.is2xxSuccessful(), this::handleNon2xxResponse).bodyToMono(responseType);
    }

    public <T> Mono<ResponseEntity<T>> putWithResponseEntityAsync(String uri, MediaType mediaType, Object params, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        return this.putAsync(uri, mediaType, params, headers, responseType).map(ResponseEntity::ok);
    }

    public <T> T syncExecute(Mono<T> mono) {
        return mono.block();
    }

    public ParameterizedTypeReference<Map<String, Object>> getMapTypeReference() {
        return new ParameterizedTypeReference<>() {
        };
    }

}
