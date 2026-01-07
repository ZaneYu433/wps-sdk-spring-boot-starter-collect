package net.thewesthill.wps.service;

import jakarta.validation.constraints.NotNull;
import net.thewesthill.wps.enums.GrantTypes;

public class Oauth2TokenRequest {

    @NotNull
    private GrantTypes grantTypes;

    private String code;

    private String redirectUri;

    private Oauth2TokenRequest() {}

    private Oauth2TokenRequest(GrantTypes grantTypes)
    {
        this.grantTypes = grantTypes;
    }

    private Oauth2TokenRequest(GrantTypes grantTypes, String code, String redirectUri) {
        this.grantTypes = grantTypes;
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public static Oauth2TokenRequest buildUserClientTokenRequest(GrantTypes grantTypes, String code, String redirectUri)
    {
        return new Oauth2TokenRequest(grantTypes, code, redirectUri);
    }

    public static Oauth2TokenRequest buildStandaloneTokenRequest(GrantTypes grantTypes)
    {
        return new Oauth2TokenRequest(grantTypes);
    }

    public String getUserClientRequestInfo()
    {
        return "GrantTypes: " + grantTypes + ", Code: " + code + ", RedirectUri: " + redirectUri;
    }

    public GrantTypes getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(GrantTypes grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
