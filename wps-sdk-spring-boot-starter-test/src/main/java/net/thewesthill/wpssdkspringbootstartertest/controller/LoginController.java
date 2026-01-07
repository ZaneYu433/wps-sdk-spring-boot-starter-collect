package net.thewesthill.wpssdkspringbootstartertest.controller;

import net.thewesthill.wps.enums.GrantTypes;
import net.thewesthill.wps.service.Oauth2TokenRequest;
import net.thewesthill.wps.service.impl.StandaloneClientTokenBuilder;
import net.thewesthill.wps.service.impl.UserClientTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StandaloneClientTokenBuilder standaloneBuilder;

    @Autowired
    private UserClientTokenBuilder userBuilder;

    @GetMapping("/getstandalonetoken")
    public String getStandaloneToken() {
        return standaloneBuilder.getWpsTokenSync(Oauth2TokenRequest.buildStandaloneTokenRequest(GrantTypes.StandaloneClient));
    }

    @GetMapping("/auth")
    public ResponseEntity<Void> Auth(@RequestParam("redirect_uri") String redirectUri,
                                     @RequestParam("scope") String scope,
                                     @RequestParam(value = "state", required = false) String state) {

        try {
            String response = userBuilder.AuthSender(redirectUri, scope, state);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", response)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Location", "/error?msg=" + ex.getMessage())
                    .build();
        }
    }
}
