package net.thewesthill.wps.model.oauth2.token.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.thewesthill.wps.model.BaseResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserTokenClientResponse extends BaseResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_expires_in")
    private String refreshExpiresIn;

    @JsonProperty("token_type")
    private String tokenType;
}
