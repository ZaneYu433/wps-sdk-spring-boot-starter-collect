package net.thewesthill.wps.model.drives.files.request_upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DrivesFilesRequestUploadRequest {

    @JsonProperty("file_id")
    private String fileId;

    private List<Hashes> hashes;

    private boolean internal;

    private String name;

    @JsonProperty("on_name_confilict")
    private String onNameConflict;

    @JsonProperty("parent_path")
    private String[] parentPath;

    @NotNull(message = "Please Input Valid Size.")
    private Integer size;

    @JsonProperty("storage_base_domain")
    private String storageBaseDomain;

    @JsonProperty("upload_scene")
    private String uploadScene;

    @Data
    public static class Hashes {

        @NotNull(message = "Please Input Valid Sum.")
        private String sum;

        @NotNull(message = "Please Input Valid Type.")
        private String type;

    }
}
