package net.thewesthill.wpssdkspringbootstartertest.controller;

import io.netty.util.internal.StringUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.model.UploadFileRequest;
import net.thewesthill.wps.model.doclibs.DocLibsRequest;
import net.thewesthill.wps.model.drive_freq.items.DriveFreqItemsRequest;
import net.thewesthill.wps.model.drivers.files.children.request.DriversFilesChildrenRequest;
import net.thewesthill.wps.model.drives.files.request_upload.DrivesFilesRequestUploadRequest;
import net.thewesthill.wps.service.impl.CloudDocClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping(value = "/to-object")
    public ResponseEntity<Map<String, Object>> postToObject(@RequestHeader(value = "Authorization") String token,
                                                            @RequestParam("file") MultipartFile file,
                                                            @ModelAttribute UploadFileRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        if (StringUtil.isNullOrEmpty(fileName)) {
            return ResponseEntity.badRequest().body(Map.of("code", 400, "msg", "Please Check Valid FileName."));
        }
        // BackUp.
        Path path = Paths.get("C:/Users/ZaneYu/Downloads");
        Path targetPath = path.resolve(fileName);
        File localBackupFile = targetPath.toFile();
        file.transferTo(localBackupFile);
        return client.postToObjectSync(new HttpHeaders() {{
            add("Authorization", token);
        }}, localBackupFile, request);
    }
}
