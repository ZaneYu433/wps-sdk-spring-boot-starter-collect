package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModifiedBy {

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("company_id")
    private String companyId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;
}
