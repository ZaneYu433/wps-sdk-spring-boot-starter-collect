package net.thewesthill.wpssdkspringbootstartertest;

import net.thewesthill.wps.KsoSign;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class WpsSdkSpringBootStarterTestApplicationTests {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, InvalidKeyException {
        final String accessKey = "AK20260112LVMYCE";
        final String secretKey = "f26560b2a21918744893dc32746d1e04";
        final String method = "GET";
        final String uri = "v7/users/current";
        final String contentType = "application/json";
        final String contentDate = "Tue, 13 Jan 2026 06:18:31 GMT";
        final byte[] requestBody = "".getBytes(StandardCharsets.UTF_8);

        KsoSign kso = new KsoSign(accessKey, secretKey);
        KsoSign.Out out = kso.kso1Sign(method, uri, contentType, contentDate, requestBody);
        System.out.println(out.date());
        System.out.println(out.authorization());
    }

}
