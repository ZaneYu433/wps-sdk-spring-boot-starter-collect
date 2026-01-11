package net.thewesthill.wps.model.drive_freq.items;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DriveFreqItemsRequest {

    private boolean with_permission;

    private boolean with_link;

    @NotNull(message = "Please Input Valid PageSize.")
    private Integer page_size;

    private String page_token;

}
