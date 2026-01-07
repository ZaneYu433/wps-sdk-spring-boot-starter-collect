package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class File {

    @JsonProperty("created_by")
    private CreatedBy createdBy;

    @JsonProperty("ctime")
    private Integer cTime;

    @JsonProperty("drive")
    private Drive drive;

    @JsonProperty("drive_id")
    private String driveId;

    @JsonProperty("ext_attrs")
    private List<ExtAttrs> extAttrs;

    @JsonProperty("id")
    private String id;

    @JsonProperty("link_id")
    private String linkId;

    @JsonProperty("link_url")
    private String linkUrl;

    @JsonProperty("modified_by")
    private ModifiedBy modifiedBy;

    @JsonProperty("mtime")
    private Integer mTime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("permission")
    private Permission permission;

    @JsonProperty("shared")
    private boolean shared;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("type")
    private String type;

    @JsonProperty("version")
    private Integer version;
}
