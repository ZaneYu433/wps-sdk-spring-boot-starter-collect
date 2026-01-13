package net.thewesthill.wps.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UploadFileRequest {

    private String method;

    private String url;

}
