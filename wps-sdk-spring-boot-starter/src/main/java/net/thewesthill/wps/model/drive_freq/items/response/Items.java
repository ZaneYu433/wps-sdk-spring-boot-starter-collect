package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Items {

    @JsonProperty("cTime")
    private Integer ctime;

    @JsonProperty("drive")
    private Drive drive;

    @JsonProperty("file")
    private File file;

    @JsonProperty("file_src")
    private FileSrc fileSrc;

    @JsonProperty("object_type")
    private String objectType;

    @JsonProperty("position")
    private Integer position;
}
