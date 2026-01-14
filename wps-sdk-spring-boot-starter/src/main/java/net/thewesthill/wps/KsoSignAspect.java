package net.thewesthill.wps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thewesthill.wps.properties.ClientCredentialsProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class KsoSignAspect {

    private final ClientCredentialsProperties properties;

    @Pointcut("@annotation(net.thewesthill.wps.KsoSignEnhance)")
    public void ksoSignPointcut() {
    }

    @Around("ksoSignPointcut()")
    public Object aroundKsoSignMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        KsoSignEnhance annotation = method.getAnnotation(KsoSignEnhance.class);

//        HttpHeaders headers = getHttpHeadersFromArgs(joinPoint.getArgs());
//
//        String rfcTime = CommonUtil.getRFC1123Date();
//        KsoSign ksoSign = new KsoSign(properties.getClientId(), properties.getClientSecret());
//        KsoSign.Out out = ksoSign.kso1Sign(annotation.httpMethod(), annotation.url(), annotation.mediaType(), rfcTime, "".getBytes(StandardCharsets.UTF_8));
//
//        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
//        headers.add("X-Kso-Date", rfcTime);
//        headers.add("X-Kso-Authorization", out.authorization());

        return joinPoint.proceed();
    }
}