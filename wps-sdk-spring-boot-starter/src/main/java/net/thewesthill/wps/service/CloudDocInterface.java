package net.thewesthill.wps.service;

import net.thewesthill.wps.model.UploadFileRequest;
import net.thewesthill.wps.model.doclibs.DocLibsRequest;
import net.thewesthill.wps.model.drive_freq.items.DriveFreqItemsRequest;
import net.thewesthill.wps.model.drivers.files.children.request.DriversFilesChildrenRequest;
import net.thewesthill.wps.model.drives.files.request_upload.DrivesFilesRequestUploadRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudDocInterface {

    ResponseEntity<Map<String, Object>> getDriveFreqItemsSync(HttpHeaders requestHeader, DriveFreqItemsRequest request);

    ResponseEntity<Map<String, Object>> getDocLibsSync(HttpHeaders requestHeader, DocLibsRequest request);

    ResponseEntity<Map<String, Object>> getDrivesFilesChildrenSync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesChildrenRequest request);

    ResponseEntity<Map<String, Object>> postDrivesFileRequestUploadSync(HttpHeaders requestHeader, String driveId, String parentId, DrivesFilesRequestUploadRequest request);

    ResponseEntity<Map<String, Object>> postToObjectSync(HttpHeaders requestHeader, File localFile, UploadFileRequest request) throws IOException;

    Mono<ResponseEntity<Map<String, Object>>> getDriveFreqItemsAsync(HttpHeaders requestHeader, DriveFreqItemsRequest request);

    Mono<ResponseEntity<Map<String, Object>>> getDocLibsAsync(HttpHeaders requestHeader, DocLibsRequest request);

    Mono<ResponseEntity<Map<String, Object>>> getDrivesFilesChildrenAsync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesChildrenRequest request);

    Mono<ResponseEntity<Map<String, Object>>> postDrivesFileRequestUploadAsync(HttpHeaders requestHeader, String driveId, String parentId, DrivesFilesRequestUploadRequest request);

    Mono<ResponseEntity<Map<String, Object>>> postToObjectAsync(HttpHeaders requestHeader, File localFile, UploadFileRequest request) throws IOException;

}
