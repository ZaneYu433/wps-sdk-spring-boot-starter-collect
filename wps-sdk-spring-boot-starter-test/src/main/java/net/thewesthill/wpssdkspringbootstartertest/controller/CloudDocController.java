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

@RequiredArgsConstructor
@RestController
@RequestMapping("/doc")
public class CloudDocController {

    private final CloudDocClient client;

    @GetMapping("/getusedfiles")
    public ResponseEntity<DriveFreqItemsResponse> getUsedFiles(@RequestHeader(value = "Authorization") String token,
                                                               DriveFreqItemsParam param) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);

        System.out.println(token);
        System.out.println(param);
        return client.GetUsedFilesSync(requestHeaders, param);
    }
}
