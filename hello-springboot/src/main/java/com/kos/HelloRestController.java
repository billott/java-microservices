package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by billott 
 */
@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix="helloapp")
public class HelloRestController {

    private String saying;

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
    public String hello() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }
}
