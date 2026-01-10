package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.service.CloudDocInterface;
import net.thewesthill.wps.utils.WebClientTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudDocClient implements CloudDocInterface {

    private final WebClientTemplate webClientTemplate;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getUsedFilesAsync(HttpHeaders headers, String withPermission, String withLink, String pageSize, String pageToken) {

        if (headers == null || headers.isEmpty()) {
            return webClientTemplate.handleHeaderResponse("Request headers cannot be null or empty (must contain token");
        }

        String token = headers.getFirst("Authorization");
        if (token == null || token.isBlank()) {
            return webClientTemplate.handleHeaderResponse("Request header 'Authorization' (token) is required");
        }

        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DRIVE_FREQ_ITEMS_URL, new LinkedMultiValueMap<>() {{
            add("with_permission", withPermission);
            add("with_link", withLink);
            add("page_size", pageSize);
            add("page_token", pageToken);
        }}, headers, webClientTemplate.getMapTypeReference());

    }

    public ResponseEntity<Map<String, Object>> GetUsedFilesSync(HttpHeaders headers, String withPermission, String withLink, String pageSize, String pageToken) {
        return webClientTemplate.syncExecute(getUsedFilesAsync(headers, withPermission, withLink, pageSize, pageToken));
    }
}
