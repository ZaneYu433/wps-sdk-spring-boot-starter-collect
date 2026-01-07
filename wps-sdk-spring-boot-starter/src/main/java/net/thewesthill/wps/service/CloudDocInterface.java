package net.thewesthill.wps.service;

import net.thewesthill.wps.model.DriveFreq.Items.request.DriveFreqItemsRequest;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CloudDocInterface {

    Mono<Map<String, Object>> GetUsedFiles(DriveFreqItemsRequest request);
}
