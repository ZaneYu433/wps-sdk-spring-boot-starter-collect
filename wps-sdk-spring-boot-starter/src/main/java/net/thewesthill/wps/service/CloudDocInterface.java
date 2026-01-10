package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Map;

@Validated
public interface CloudDocInterface {

    Mono<ResponseEntity<Map<String, Object>>> getUsedFilesAsync(@Valid HttpHeaders headers, String withPermission, String withLink, String pageSize, String pageToken);

}
