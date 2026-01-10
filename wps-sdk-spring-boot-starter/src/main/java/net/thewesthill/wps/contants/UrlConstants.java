package net.thewesthill.wps.contants;

public interface UrlConstants {

    /**
     * 授权认证接口地址
     */
    String WPS_TOKEN_URL = "/oauth2/token";

    /**
     * 授权跳转链接
     */
    String OAUTH2_AUTH_URL = "/oauth2/auth";

    /**
     * 获取常用文档列表
     */
    String DRIVE_FREQ_ITEMS_URL = "/v7/drive_freq/items";

    /**
     * 获取用户信息
     */
    String USER_CURRENT_URL = "/v7/users/current";

    /**
     * 获取团队文档库列表
     */
    String DOC_LIBS_URL = "/v7/doclibs";
}
