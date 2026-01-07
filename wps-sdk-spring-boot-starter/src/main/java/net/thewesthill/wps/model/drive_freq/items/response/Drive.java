package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Drive {

    @JsonProperty("allotee_id")
    private String alloteeId;

    @JsonProperty("allotee_type")
    private String alloteeType;

    @JsonProperty("company_id")
    private String companyId;

    @JsonProperty("created_by")
    private CreatedBy createdBy;

    @JsonProperty("ctime")
    private Integer cTime;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ext_attrs")
    private List<ExtAttrs> extAttrs;

    @JsonProperty("id")
    private String id;

    @JsonProperty("mtime")
    private Integer mTime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("quota")
    private Quota quota;

    @JsonProperty("source")
    private String source;

    @JsonProperty("status")
    private String status;
}
