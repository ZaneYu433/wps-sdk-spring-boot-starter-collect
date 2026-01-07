package net.thewesthill.wps.service;

import jakarta.validation.Valid;
import net.thewesthill.wps.model.drive_freq.items.request.DriveFreqItemsParam;
import net.thewesthill.wps.model.drive_freq.items.response.DriveFreqItemsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
public interface CloudDocInterface {

    Mono<ResponseEntity<DriveFreqItemsResponse>> GetUsedFilesAsync(@Valid HttpHeaders headers, DriveFreqItemsParam request);
}
