package net.thewesthill.wpssdkspringbootstartertest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.model.DocLibsRequest;
import net.thewesthill.wps.model.drivers.files.children.request.DriversFilesChildrenRequest;
import net.thewesthill.wps.service.impl.CloudDocClient;
import net.thewesthill.wps.utils.CommonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cloud-doc")
public class CloudDocController {

    private final CloudDocClient client;

    @GetMapping("/drive-freq-items")
    public ResponseEntity<Map<String, Object>> getUsedFiles(@RequestHeader(value = "Authorization") String token,
                                                            @RequestHeader(value = "X-Kso-Id-Type", required = false) String type,
                                                            @RequestParam(value = "with_permission", required = false) String withPermission,
                                                            @RequestParam(value = "with_link", required = false) String withLink,
                                                            @RequestParam(value = "page_size", defaultValue = "1") String pageSize,
                                                            @RequestParam(value = "page_token", required = false) String pageToken) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
            add("X-Kso-Id-Type", type);
        }};
        return client.getUsedFilesSync(requestHeaders, withPermission, withLink, pageSize, pageToken);
    }

    @GetMapping("/doclibs")
    public ResponseEntity<Map<String, Object>> getGroupLibs(@RequestHeader("Authorization") String token,
                                                            @Validated @ModelAttribute DocLibsRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
        }};
        return client.getDocLibsSync(requestHeaders, request);
    }

    @GetMapping("/drives/{drive_id}/files/{parent_id}/children")
    public ResponseEntity<Map<String, Object>> getDriveFiles(@RequestHeader("Authorization") String token,
                                                             @RequestHeader(value = "X-Kso-Id-Type", required = false) String type,
                                                             @PathVariable("drive_id") String driveId,
                                                             @PathVariable("parent_id") String parentId,
                                                             @Validated @ModelAttribute DriversFilesChildrenRequest request) {
        System.out.println(CommonUtil.pojoCover(request));
        return ResponseEntity.ok(null);
    }
}
