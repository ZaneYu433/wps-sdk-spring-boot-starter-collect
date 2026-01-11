package net.thewesthill.wps.model;

import lombok.Data;

@Data
public class UploadFileRequest {

    private Data data;

    @lombok.Data
    public static class Data {

        private StoreRequest storeRequest;

        private String uploadId;
    }

    public static class StoreRequest {

        private String method;

        private String url;
    }
}
