package net.thewesthill.wps;

import net.thewesthill.wps.contants.UrlConstants;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KsoSignEnhance {

    String httpMethod() default KsoSignArgs.REQUEST_WAY.GET;

    String url() default UrlConstants.OAUTH2_AUTH_URL;

    String mediaType() default KsoSignArgs.CONTENT_TYPE.APPLICATION_JSON;

}