package net.thewesthill.wps.model.drive_freq.items.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveFreqItemsParam {

    @JsonProperty("with_permission")
    private boolean withPermission;

    @JsonProperty("with_link")
    private boolean withLink;

    @NotNull
    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("page_token")
    private String pageToken;
}
