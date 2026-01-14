package net.thewesthill.wps.model.drives.files.commit_upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DriversFilesCommitUploadRequest {

    private String id;

    @JsonProperty("upload_id")
    private String uploadId;

}
