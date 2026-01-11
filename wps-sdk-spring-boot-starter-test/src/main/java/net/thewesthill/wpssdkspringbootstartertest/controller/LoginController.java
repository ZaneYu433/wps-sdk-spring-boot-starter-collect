package net.thewesthill.wpssdkspringbootstartertest.controller;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.enums.GrantTypes;
import net.thewesthill.wps.service.Oauth2TokenParam;
import net.thewesthill.wps.service.impl.StandaloneAccessTokenClient;
import net.thewesthill.wps.service.impl.UserAccessTokenClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final StandaloneAccessTokenClient standaloneBuilder;

    private final UserAccessTokenClient userClient;

    @GetMapping("/get-standalone-token")
    public ResponseEntity<Map<String, Object>> getStandaloneToken() {
        return standaloneBuilder.getWpsTokenSync(Oauth2TokenParam.buildStandaloneTokenRequest(GrantTypes.StandaloneClient));
    }

    @GetMapping("/oauth2/auth")
    public ResponseEntity<Void> getOauth2Auth(@RequestParam(value = "redirect_uri", defaultValue = "http://localhost:8080/login/callback") String redirectUri,
                                              @RequestParam("scope") String scope,
                                              @RequestParam(value = "state", required = false) String state) {
        try {
            String response = userClient.getOauth2AuthSync(redirectUri, scope, state);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", response)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Location", "/error?msg=" + ex.getMessage())
                    .build();
        }
    }

    @GetMapping("/callback")
    public ResponseEntity<Map<String, Object>> callback(@RequestParam("code") String code,
                                                        @RequestParam(value = "redirect_uri", defaultValue = "http://localhost:8080/login/callback") String redirectUri) {
        return userClient.getWpsTokenSync(Oauth2TokenParam.buildUserClientTokenRequest(GrantTypes.UserClient, code, redirectUri));
    }

    @GetMapping("/refresh-user-token")
    public ResponseEntity<Map<String, Object>> refreshUserToken(@RequestParam("refresh_token") String refreshToken) {
        return userClient.refreshTokenSync(Oauth2TokenParam.buildRefreshUserTokenRequest(GrantTypes.RefreshClient, refreshToken));
    }

}
