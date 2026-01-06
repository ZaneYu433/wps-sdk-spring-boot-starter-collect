package net.thewesthill.wps.enums;

public enum GrantTypes {

    UserClient("authorization_code"),

    RefreshClient("refresh_token"),

    StandaloneClient("client_credentials");

    private final String info;

    GrantTypes(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
