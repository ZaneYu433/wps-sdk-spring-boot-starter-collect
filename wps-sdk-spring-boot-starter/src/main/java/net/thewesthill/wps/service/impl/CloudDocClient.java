package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.model.drive_freq.items.request.DriveFreqItemsParam;
import net.thewesthill.wps.model.drive_freq.items.response.DriveFreqItemsResponse;
import net.thewesthill.wps.service.CloudDocInterface;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudDocClient implements CloudDocInterface {

    private final WebClient webClient;

    @Override
    public Mono<ResponseEntity<DriveFreqItemsResponse>> GetUsedFilesAsync(HttpHeaders headers,
                                                                          DriveFreqItemsParam request) {

        if (headers == null || headers.isEmpty()) {
            return Mono.error(new IllegalAccessError("Request headers cannot be null or empty (must contain token"));
        }

        String token = headers.getFirst("Authorization");
        if (token == null || token.isBlank()) {
            return Mono.error(new IllegalArgumentException("Request header 'Authorization' (token) is required"));
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(UrlConstants.DRIVE_FREQ_ITEMS_URL)
                        .queryParamIfPresent("with_permission", Optional.of(request.isWithPermission()))
                        .queryParamIfPresent("with_link", Optional.of(request.isWithLink()))
                        .queryParam("page_size", request.getPageSize())
                        .queryParam("page_token", request.getPageToken()).build()
                )
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Request Error: " + response.statusCode()))
                )
                .bodyToMono(DriveFreqItemsResponse.class)
                .map(ResponseEntity::ok);
    }

    public ResponseEntity<DriveFreqItemsResponse> GetUsedFilesSync(HttpHeaders headers,
                                                                   DriveFreqItemsParam request) {
        Mono<ResponseEntity<DriveFreqItemsResponse>> responseEntityMono = GetUsedFilesAsync(headers, request);
        return responseEntityMono.block();
    }
}
