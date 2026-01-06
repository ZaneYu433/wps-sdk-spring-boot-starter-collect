package net.thewesthill.wps.annotation;

import net.thewesthill.wps.config.WpsSdkAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({WpsSdkAutoConfiguration.class})
public @interface EnableWpsServe {
}
