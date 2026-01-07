package net.thewesthill.wps.service;

import net.thewesthill.wps.model.drive_freq.items.request.DriveFreqItemsParam;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CloudDocInterface {

    Mono<Map<String, Object>> GetUsedFiles(DriveFreqItemsParam request);
}
