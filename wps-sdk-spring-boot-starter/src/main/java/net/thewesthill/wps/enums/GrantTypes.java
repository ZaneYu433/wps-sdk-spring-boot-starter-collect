package net.thewesthill.wps.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GrantTypes {

    UserClient("authorization_code"),

    RefreshClient("refresh_token"),

    StandaloneClient("client_credentials");

    private final String info;

}
