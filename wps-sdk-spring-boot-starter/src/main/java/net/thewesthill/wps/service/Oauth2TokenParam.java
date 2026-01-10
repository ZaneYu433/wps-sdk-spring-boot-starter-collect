package net.thewesthill.wps.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thewesthill.wps.enums.GrantTypes;

@Data
@NoArgsConstructor
public class Oauth2TokenParam {

    @NotNull
    private GrantTypes grantTypes;

    private String code;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private Oauth2TokenParam(GrantTypes grantTypes) {
        this.grantTypes = grantTypes;
    }

    private Oauth2TokenParam(GrantTypes grantTypes, String code, String redirectUri) {
        this.grantTypes = grantTypes;
        this.code = code;
        this.redirectUri = redirectUri;
    }

    private Oauth2TokenParam(GrantTypes grantTypes, String refreshToken) {
        this.grantTypes = grantTypes;
        this.refreshToken = refreshToken;
    }

    public static Oauth2TokenParam buildUserClientTokenRequest(GrantTypes grantTypes, String code, String redirectUri) {
        return new Oauth2TokenParam(grantTypes, code, redirectUri);
    }

    public static Oauth2TokenParam buildStandaloneTokenRequest(GrantTypes grantTypes) {
        return new Oauth2TokenParam(grantTypes);
    }

    public static Oauth2TokenParam buildRefreshUserTokenRequest(GrantTypes grantTypes, String refreshToken) {
        return new Oauth2TokenParam(grantTypes, refreshToken);
    }
}
