package net.thewesthill.wps.service.impl;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.CommonUtil;
import net.thewesthill.wps.components.WebClientTemplate;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.model.UploadFileRequest;
import net.thewesthill.wps.model.doclibs.DocLibsRequest;
import net.thewesthill.wps.model.drive_freq.items.DriveFreqItemsRequest;
import net.thewesthill.wps.model.drivers.files.children.request.DriversFilesChildrenRequest;
import net.thewesthill.wps.model.drives.files.commit_upload.DriversFilesCommitUploadRequest;
import net.thewesthill.wps.model.drives.files.request_upload.DrivesFilesRequestUploadRequest;
import net.thewesthill.wps.service.CloudDocInterface;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudDocClient implements CloudDocInterface {

    private final WebClientTemplate webClientTemplate;

    @Override
    public ResponseEntity<Map<String, Object>> getDriveFreqItemsSync(HttpHeaders requestHeader, DriveFreqItemsRequest request) {
        return webClientTemplate.syncExecute(getDriveFreqItemsAsync(requestHeader, request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDocLibsSync(HttpHeaders requestHeader, DocLibsRequest request) {
        return webClientTemplate.syncExecute(getDocLibsAsync(requestHeader, request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDrivesFilesChildrenSync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesChildrenRequest request) {
        return webClientTemplate.syncExecute(getDrivesFilesChildrenAsync(requestHeader, driveId, parentId, request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> postDrivesFileRequestUploadSync(HttpHeaders requestHeader, String driveId, String parentId, DrivesFilesRequestUploadRequest request) {
        return webClientTemplate.syncExecute(postDrivesFileRequestUploadAsync(requestHeader, driveId, parentId, request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> putToObjectSync(HttpHeaders requestHeader, File localFile, UploadFileRequest request) {
        return webClientTemplate.syncExecute(putToObjectAsync(requestHeader, localFile, request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> postDriversFilesCommitUploadSync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesCommitUploadRequest request) {
        return webClientTemplate.syncExecute(postDriversFilesCommitUploadAsync(requestHeader, driveId, parentId, request));
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getDriveFreqItemsAsync(HttpHeaders requestHeader, DriveFreqItemsRequest request) {
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DRIVE_FREQ_ITEMS_URL, CommonUtil.pojoCover(request), requestHeader, webClientTemplate.getMapTypeReference());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getDocLibsAsync(HttpHeaders requestHeader, DocLibsRequest request) {
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DOC_LIBS_URL, CommonUtil.pojoCover(request), requestHeader, webClientTemplate.getMapTypeReference());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> getDrivesFilesChildrenAsync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesChildrenRequest request) {
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.DRIVERS_DRIVE_ID_FILES_PARENT_ID_CHILDREN(driveId, parentId), CommonUtil.pojoCover(request), requestHeader, webClientTemplate.getMapTypeReference());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> postDrivesFileRequestUploadAsync(HttpHeaders requestHeader, String driveId, String parentId, DrivesFilesRequestUploadRequest request) {
        return webClientTemplate.postWithResponseEntityAsync(UrlConstants.DRIVERS_DRIVE_ID_FILES_PARENT_ID_REQUEST_UPLOAD(driveId, parentId), MediaType.APPLICATION_JSON, request, requestHeader, webClientTemplate.getMapTypeReference());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> putToObjectAsync(HttpHeaders requestHeader, File localFile, UploadFileRequest request) {

        FileSystemResource fileResource = new FileSystemResource(localFile);

        return webClientTemplate.putWithResponseEntityAsync(request.getUrl(), MediaType.APPLICATION_OCTET_STREAM, fileResource, requestHeader, webClientTemplate.getMapTypeReference());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> postDriversFilesCommitUploadAsync(HttpHeaders requestHeader, String driveId, String parentId, DriversFilesCommitUploadRequest request) {
        return webClientTemplate.postWithResponseEntityAsync(UrlConstants.DRIVERS_DRIVE_ID_FILES_PARENT_ID_COMMIT_UPLOAD(driveId, parentId), MediaType.APPLICATION_JSON, request, requestHeader, webClientTemplate.getMapTypeReference());
    }
}
