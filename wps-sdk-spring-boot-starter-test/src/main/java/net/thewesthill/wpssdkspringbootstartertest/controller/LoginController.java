package net.thewesthill.wpssdkspringbootstartertest.controller;

import net.thewesthill.wps.service.impl.StandaloneClientTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StandaloneClientTokenBuilder builder;

    @GetMapping("/standalonetoken")
    public String getStandaloneToken(@RequestParam("grantType") String grantType)
    {
        System.out.println(111);
        return builder.getWpsTokenSync(grantType);
    }
}
