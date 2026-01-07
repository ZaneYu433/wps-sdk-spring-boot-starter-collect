package net.thewesthill.wps.service.impl;

import net.thewesthill.wps.model.DriveFreq.Items.request.DriveFreqItemsRequest;
import net.thewesthill.wps.service.CloudDocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CloudDocClient implements CloudDocInterface {

    @Autowired
    private WebClient webClient;


    @Override
    public Mono<Map<String, Object>> GetUsedFiles(DriveFreqItemsRequest request) {
        return null;
    }
}
