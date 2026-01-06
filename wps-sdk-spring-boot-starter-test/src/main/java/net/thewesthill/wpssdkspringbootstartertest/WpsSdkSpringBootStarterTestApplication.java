package net.thewesthill.wpssdkspringbootstartertest;

import net.thewesthill.wps.annotation.EnableWpsServe;
import net.thewesthill.wps.service.impl.StandaloneClientTokenBuilder;
import net.thewesthill.wps.service.impl.UserClientTokenBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableWpsServe
@SpringBootApplication
public class WpsSdkSpringBootStarterTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WpsSdkSpringBootStarterTestApplication.class, args);
        StandaloneClientTokenBuilder bean = run.getBean(StandaloneClientTokenBuilder.class);
        System.out.println(bean.getWpsToken("client_credentials"));
    }

}
