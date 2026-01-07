package net.thewesthill.wps.model.oauth2.token.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.thewesthill.wps.model.BaseResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public class StandaloneTokenClientResponse extends BaseResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("token_type")
    private String tokenType;
}
