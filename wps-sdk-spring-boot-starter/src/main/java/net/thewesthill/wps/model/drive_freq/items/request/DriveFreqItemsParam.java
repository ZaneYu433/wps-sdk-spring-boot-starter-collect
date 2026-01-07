package net.thewesthill.wps.model.drive_freq.items.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveFreqItemsParam {

    private boolean withPermission;

    private boolean withLink;

    @NotNull
    private int pageSize;

    private String pageToken;
}
