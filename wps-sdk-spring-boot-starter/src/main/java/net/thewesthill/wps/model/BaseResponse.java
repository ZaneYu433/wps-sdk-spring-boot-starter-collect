package net.thewesthill.wps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponse {

    @JsonProperty("code")
    private Integer code = 0;

    @JsonProperty("msg")
    private String msg;
}
