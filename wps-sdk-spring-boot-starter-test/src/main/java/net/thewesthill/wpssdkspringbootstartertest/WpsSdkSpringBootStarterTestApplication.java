package net.thewesthill.wpssdkspringbootstartertest;

import net.thewesthill.wps.annotation.EnableWpsServe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWpsServe
@SpringBootApplication
public class WpsSdkSpringBootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpsSdkSpringBootStarterTestApplication.class, args);
    }

}
