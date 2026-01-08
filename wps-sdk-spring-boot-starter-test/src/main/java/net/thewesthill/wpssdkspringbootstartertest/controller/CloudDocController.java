package net.thewesthill.wpssdkspringbootstartertest.controller;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.model.drive_freq.items.request.DriveFreqItemsParam;
import net.thewesthill.wps.model.drive_freq.items.response.DriveFreqItemsResponse;
import net.thewesthill.wps.service.impl.CloudDocClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 云文档
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/doc")
public class CloudDocController {

    private final CloudDocClient client;

    /**
     * 获取常用列表
     *
     * @param token 授权 token
     * @param param with_permission with_link page_size page_token
     * @return
     */
    @GetMapping("/getusedfiles")
    public ResponseEntity<DriveFreqItemsResponse> getUsedFiles(@RequestHeader(value = "Authorization") String token,
                                                               DriveFreqItemsParam param) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
        }};
        return client.GetUsedFilesSync(requestHeaders, param);
    }
}
