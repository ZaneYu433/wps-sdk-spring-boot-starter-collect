package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Permission {

    @JsonProperty("comment")
    private Boolean comment;

    @JsonProperty("copy")
    private Boolean copy;

    @JsonProperty("copy_content")
    private Boolean copyContent;

    @JsonProperty("delete")
    private Boolean delete;

    @JsonProperty("download")
    private Boolean download;

    @JsonProperty("history")
    private Boolean history;

    @JsonProperty("list")
    private Boolean list;

    @JsonProperty("move")
    private Boolean move;

    @JsonProperty("new_empty")
    private Boolean newEmpty;

    @JsonProperty("perm_ctl")
    private Boolean permCtl;

    @JsonProperty("preview")
    private Boolean preview;

    @JsonProperty("print")
    private Boolean print;

    @JsonProperty("rename")
    private Boolean rename;

    @JsonProperty("saveas")
    private Boolean saveAs;

    @JsonProperty("secret")
    private Boolean secret;

    @JsonProperty("share")
    private Boolean share;

    @JsonProperty("update")
    private Boolean update;

    @JsonProperty("upload")
    private Boolean upload;
}
