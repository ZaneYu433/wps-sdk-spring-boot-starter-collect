package net.thewesthill.wpssdkspringbootstartertest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.model.doclibs.DocLibsRequest;
import net.thewesthill.wps.model.drive_freq.items.DriveFreqItemsRequest;
import net.thewesthill.wps.model.drivers.files.children.request.DriversFilesChildrenRequest;
import net.thewesthill.wps.model.drives.files.request_upload.DrivesFilesRequestUploadRequest;
import net.thewesthill.wps.service.impl.CloudDocClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cloud-doc")
public class CloudDocController {

    private final CloudDocClient client;

    @GetMapping("/drive-freq/items")
    public ResponseEntity<Map<String, Object>> getDriveFreqItems(@RequestHeader(value = "Authorization") String token,
                                                                 @RequestHeader(value = "X-Kso-Id-Type", required = false) String type,
                                                                 @Valid @ModelAttribute DriveFreqItemsRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
            add("X-Kso-Id-Type", type);
        }};
        return client.getDriveFreqItemsSync(requestHeaders, request);
    }

    @GetMapping("/doclibs")
    public ResponseEntity<Map<String, Object>> getDocLibs(@RequestHeader(value = "Authorization") String token,
                                                          @Valid @ModelAttribute DocLibsRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders() {{
            add("Authorization", token);
        }};
        return client.getDocLibsSync(requestHeaders, request);
    }

    @GetMapping("/drives/{drive_id}/files/{parent_id}/children")
    public ResponseEntity<Map<String, Object>> getDrivesFilesChildren(@RequestHeader(value = "Authorization") String token,
                                                                      @RequestHeader(value = "X-Kso-Id-Type", required = false) String type,
                                                                      @PathVariable(value = "drive_id") String driveId,
                                                                      @PathVariable(value = "parent_id") String parentId,
                                                                      @Valid @ModelAttribute DriversFilesChildrenRequest request) {
        HttpHeaders requestHeader = new HttpHeaders() {{
            add("Authorization", token);
            add("X-Kso-Id-Type", type);
        }};
        return client.getDrivesFilesChildrenSync(requestHeader, driveId, parentId, request);
    }

    @PostMapping("/drives/{drive_id}/files/{parent_id}/request_upload")
    public ResponseEntity<Map<String, Object>> postDrivesFileRequestUpload(@RequestHeader(value = "Authorization") String token,
                                                                           @PathVariable(value = "drive_id") String driveId,
                                                                           @PathVariable(value = "parent_id") String parentId,
                                                                           @Valid @RequestBody DrivesFilesRequestUploadRequest request) {
        HttpHeaders requestHeader = new HttpHeaders() {{
            add("Authorization", token);
        }};
        return client.postDrivesFileRequestUploadSync(requestHeader, driveId, parentId, request);
    }

    @PutMapping("/to-object")
    public ResponseEntity<Map<String, Object>> postToObject(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(null);
    }
}
