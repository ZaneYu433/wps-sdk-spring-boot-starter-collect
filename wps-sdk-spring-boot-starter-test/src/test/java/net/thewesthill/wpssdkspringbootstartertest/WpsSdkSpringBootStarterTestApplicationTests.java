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
        final String accessKey = "Ak123456";
        final String secretKey = "sk098765";
        final String method = "POST";
        final String uri = "/v7/test/body";
        final String contentType = "application/json";
        final String contentDate = "Mon, 02 Jan 2006 15:04:05 GMT";
        final byte[] requestBody = "{\"key\": \"value\"}".getBytes(StandardCharsets.UTF_8);

        KsoSign kso = new KsoSign(accessKey, secretKey);
        KsoSign.Out out = kso.ksoSign(method, uri, contentType, contentDate, requestBody);
        System.out.println(out.date());
        System.out.println(out.authorization());
    }

}
