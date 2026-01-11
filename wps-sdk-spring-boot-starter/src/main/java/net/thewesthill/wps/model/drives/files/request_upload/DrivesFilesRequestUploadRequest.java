package net.thewesthill.wps.model.drives.files.request_upload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DrivesFilesRequestUploadRequest {

    private String fileId;

    private List<Hashes> hashes;

    private boolean internal;

    private String name;

    private String onNameConflict;

    private String[] parentPath;

    @NotNull(message = "Please Input Valid Size.")
    private Integer size;

    private String storageBaseDomain;

    private String uploadScene;

    @Data
    public static class Hashes {

        @NotNull(message = "Please Input Valid Sum.")
        private String sum;

        @NotNull(message = "Please Input Valid Type.")
        private String type;

    }
}
