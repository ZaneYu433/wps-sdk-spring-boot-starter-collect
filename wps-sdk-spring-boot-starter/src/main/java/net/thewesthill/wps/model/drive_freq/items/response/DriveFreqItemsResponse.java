package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import net.thewesthill.wps.model.BaseResponse;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class DriveFreqItemsResponse extends BaseResponse {

    @JsonPropertyOrder("data")
    private Data data;
}
