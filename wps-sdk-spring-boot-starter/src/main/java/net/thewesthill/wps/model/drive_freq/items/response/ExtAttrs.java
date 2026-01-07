package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExtAttrs {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}
