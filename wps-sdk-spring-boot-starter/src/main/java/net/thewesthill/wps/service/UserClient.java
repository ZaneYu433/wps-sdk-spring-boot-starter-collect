package net.thewesthill.wps.service;

import lombok.RequiredArgsConstructor;
import net.thewesthill.wps.CommonUtil;
import net.thewesthill.wps.KsoSign;
import net.thewesthill.wps.components.WebClientTemplate;
import net.thewesthill.wps.contants.UrlConstants;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final ClientCredentialsProperties properties;

    private final WebClientTemplate webClientTemplate;

    public Mono<ResponseEntity<Map<String, Object>>> getUserInfo(HttpHeaders headers) throws NoSuchAlgorithmException, InvalidKeyException {
        String rfcTime = CommonUtil.getRFC1123Date();
        KsoSign ksoSign = new KsoSign(properties.getClientId(), properties.getClientSecret());
        KsoSign.Out out = ksoSign.ksoSign("GET", UrlConstants.USER_CURRENT_URL, MediaType.APPLICATION_JSON.toString(), rfcTime, "".getBytes(StandardCharsets.UTF_8));
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("X-Kso-Date", rfcTime);
        headers.add("X-Kso-Authorization", out.authorization());
        return webClientTemplate.getWithResponseEntityAsync(UrlConstants.USER_CURRENT_URL, new LinkedMultiValueMap<>(), headers, webClientTemplate.getMapTypeReference());
    }

    public ResponseEntity<Map<String, Object>> getUserInfoSync(HttpHeaders headers) throws NoSuchAlgorithmException, InvalidKeyException {
        return webClientTemplate.syncExecute(getUserInfo(headers));
    }
}
