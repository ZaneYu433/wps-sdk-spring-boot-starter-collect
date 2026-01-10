package net.thewesthill.wps.service.impl;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.components.WebClientTemplate;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.model.DocLibsRequest;
import net.thewesthill.wps.service.CloudDocInterface;
import net.thewesthill.wps.utils.CommonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudDocClient implements CloudDocInterface {

    private final WebClientTemplate webClientTemplate;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getUsedFilesAsync(HttpHeaders headers, String withPermission, String withLink, String pageSize, String pageToken) {
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DRIVE_FREQ_ITEMS_URL, new LinkedMultiValueMap<>() {{
            if (!StringUtil.isNullOrEmpty(withPermission)) add("with_permission", withPermission);
            if (!StringUtil.isNullOrEmpty(withLink)) add("with_link", withLink);
            add("page_size", pageSize);
            if (!StringUtil.isNullOrEmpty(pageToken)) add("page_token", pageToken);
        }}, headers, webClientTemplate.getMapTypeReference());

    }

    public ResponseEntity<Map<String, Object>> getUsedFilesSync(HttpHeaders headers, String withPermission, String withLink, String pageSize, String pageToken) {
        return webClientTemplate.syncExecute(getUsedFilesAsync(headers, withPermission, withLink, pageSize, pageToken));
    }

    public Mono<ResponseEntity<Map<String, Object>>> getDocLibsASync(HttpHeaders headers, DocLibsRequest request) {
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DOC_LIBS_URL, CommonUtil.pojoCover(request), headers, webClientTemplate.getMapTypeReference());
    }

    public ResponseEntity<Map<String, Object>> getDocLibsSync(HttpHeaders headers, DocLibsRequest request) {
        return webClientTemplate.syncExecute(getDocLibsASync(headers, request));
    }
}
