package net.thewesthill.wpssdkspringbootstartertest.controller;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.service.impl.CloudDocClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            if (!StringUtil.isNullOrEmpty(type)) add("X-Kso-Id-Type", type);
        }};
        return client.getUsedFilesSync(requestHeaders, withPermission, withLink, pageSize, pageToken);
    }

    @GetMapping("/doclibs")
    public ResponseEntity<Map<String, Object>> getGroupLibs(@RequestHeader("Authorization") String token,
                                                            @RequestParam("page_size") Integer pageSize,
                                                            @RequestParam(value = "page_token", required = false) String pageToken,
                                                            @RequestParam(value = "user_role", required = false) String[] userRole) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
        }};
        return client.getDocLibsSync(requestHeaders, pageSize, pageToken, userRole);
    }
}
