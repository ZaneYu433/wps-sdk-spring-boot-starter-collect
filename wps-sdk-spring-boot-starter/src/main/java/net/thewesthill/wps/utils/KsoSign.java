package net.thewesthill.wps.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@AllArgsConstructor
public class KsoSign {

    private final String accessKey;

    private final String secretKey;

    public Out ksoSign(String method, String uri, String contentType, String ksoDate, byte[] requestBody) throws NoSuchAlgorithmException, InvalidKeyException {
        String ksoSignature = getKso1Signature(method, uri, contentType, ksoDate, requestBody);
        String authorization = String.format("KSO-1 %S:%S", accessKey, ksoSignature);
        return new Out(ksoDate, authorization);
    }

    public String getKso1Signature(String method, String uri, String contentType, String ksoDate, byte[] requestBody) throws NoSuchAlgorithmException, InvalidKeyException {
        String sha256Hex = "";
        if (requestBody != null && requestBody.length > 0) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(requestBody);
            sha256Hex = bytesToHex(hash);
        }

        String dateToSign = "KSO-1 " + method + uri + contentType + ksoDate + sha256Hex;
        log.info("DateToSign: {}", dateToSign);
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] macBytes = mac.doFinal(dateToSign.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(macBytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public record Out(String date, String authorization) {

    }

}
