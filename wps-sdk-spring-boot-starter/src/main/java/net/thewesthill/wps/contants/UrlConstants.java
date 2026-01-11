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

    /**
     * 获取子文件列表
     *
     * @param driveId  驱动盘 id
     * @param parentId 文件夹 id（根目录时为0）
     * @return wps api addr
     */
    static String DRIVERS_DRIVE_ID_FILES_PARENT_ID_CHILDREN(String driveId, String parentId) {
        return String.format("%s/children", UrlUtil.DRIVERS_DRIVE_ID_FILES_PARENT_ID(driveId, parentId));
    }

    static String DRIVERS_DRIVE_ID_FILES_PARENT_ID_REQUEST_UPLOAD(String driveId, String parentId) {
        return String.format("%s/request_upload", UrlUtil.DRIVERS_DRIVE_ID_FILES_PARENT_ID(driveId, parentId));
    }
}

final class UrlUtil {

    /**
     * 文件空间地址
     *
     * @param driveId  驱动盘 id
     * @param parentId 文件夹 id（根目录时为0）
     * @return wps api addr
     */
    public static String DRIVERS_DRIVE_ID_FILES_PARENT_ID(String driveId, String parentId) {
        return String.format("/v7/drives/%s/files/%s", driveId, parentId);
    }

}
