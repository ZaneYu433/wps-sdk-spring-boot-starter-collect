package net.thewesthill.wps.model.drivers.files.children.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DriversFilesChildrenRequest {

    private boolean with_permission;

    private boolean with_ext_attrs;

    private String filter_exts;

    private Object filter_type;

    private String order;

    private String order_by;

    @NotNull(message = "Please Input Valid PageSize.")
    private Integer page_size;

    private String page_token;
}