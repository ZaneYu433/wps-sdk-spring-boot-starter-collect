package net.thewesthill.wpssdkspringbootstartertest.controller;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.service.UserClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserClient client;

    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String token) throws NoSuchAlgorithmException, InvalidKeyException {
        return client.getUserInfoSync(new HttpHeaders() {
            {
                add("Authorization", token);
            }
        });
    }
}
