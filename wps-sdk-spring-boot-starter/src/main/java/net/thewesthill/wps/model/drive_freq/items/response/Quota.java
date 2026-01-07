package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Quota {

    @JsonProperty("deleted")
    private Integer deleted;

    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("used")
    private Integer used;
}
