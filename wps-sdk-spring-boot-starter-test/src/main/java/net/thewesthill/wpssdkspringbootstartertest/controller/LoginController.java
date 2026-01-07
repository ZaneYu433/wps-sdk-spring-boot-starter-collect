package net.thewesthill.wpssdkspringbootstartertest.controller;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.enums.GrantTypes;
import net.thewesthill.wps.model.oauth2.token.response.StandaloneTokenClientResponse;
import net.thewesthill.wps.model.oauth2.token.response.UserTokenClientResponse;
import net.thewesthill.wps.model.oauth2.token.request.Oauth2TokenParam;
import net.thewesthill.wps.service.impl.StandaloneAccessTokenClient;
import net.thewesthill.wps.service.impl.UserAccessTokenClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 授权
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final StandaloneAccessTokenClient standaloneBuilder;

    private final UserAccessTokenClient userBuilder;

    /**
     * 获取自建应用 access_token
     *
     * @return access_token
     */
    @GetMapping("/getstandalonetoken")
    public ResponseEntity<StandaloneTokenClientResponse> getStandaloneToken() {
        return standaloneBuilder.getWpsTokenSync(Oauth2TokenParam.buildStandaloneTokenRequest(GrantTypes.StandaloneClient));
    }

    /**
     * 授权链接
     * TODO 前端开发后调整
     *
     * @param redirectUri 使用开者后台应用配置的【用户授权回调地址】经过encode后的值
     * @param scope       用户授权的权限，使用英文逗号分隔，如：scope1,scope2,scope3
     * @param state       应用自定义数据，授权成功后重定向时会带出
     * @return null
     */
    @GetMapping("/auth")
    public ResponseEntity<Void> auth(@RequestParam(value = "redirect_uri", defaultValue = "http://localhost:8080/login/callback") String redirectUri,
                                     @RequestParam("scope") String scope,
                                     @RequestParam(value = "state", required = false) String state) {
        try {
            String response = userBuilder.authSender(redirectUri, scope, state);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", response)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Location", "/error?msg=" + ex.getMessage())
                    .build();
        }
    }

    /**
     * 获取用户 access_token
     * TODO 前端开发后调整
     *
     * @param code        授权链接重定向时携带的临时码
     * @param redirectUri 用于校验 code 对应的重定向地址
     * @return access_token
     */
    @GetMapping("/callback")
    public ResponseEntity<UserTokenClientResponse> callback(@RequestParam("code") String code,
                                                            @RequestParam(value = "redirect_uri", defaultValue = "http://localhost:8080/login/callback") String redirectUri) {
        // 临时处理 原流程 ==> auth.redirect_uri 前端回调页面 => code,state ==> 截取路由获取token
        // 存入redis
        return userBuilder.getWpsTokenSync(Oauth2TokenParam.buildUserClientTokenRequest(GrantTypes.UserClient, code, redirectUri));
    }

    /**
     * 刷新用户 access_token
     *
     * @param refreshToken refresh_token 的值
     * @return access_token
     */
    @GetMapping("/refreshusertoken")
    public Map<String, Object> refreshUserToken(@RequestParam("refresh_token") String refreshToken) {
        return userBuilder.refreshTokenSync(Oauth2TokenParam.buildRefreshUserTokenRequest(GrantTypes.RefreshClient, refreshToken));
    }

}
