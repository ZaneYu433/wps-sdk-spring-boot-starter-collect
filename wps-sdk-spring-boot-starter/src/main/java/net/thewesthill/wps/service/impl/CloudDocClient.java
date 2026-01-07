package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.model.drive_freq.items.request.DriveFreqItemsParam;
import net.thewesthill.wps.service.CloudDocInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudDocClient implements CloudDocInterface {

    private final WebClient webClient;

    @Override
    public Mono<Map<String, Object>> GetUsedFiles(DriveFreqItemsParam request) {
        return null;
    }
}
