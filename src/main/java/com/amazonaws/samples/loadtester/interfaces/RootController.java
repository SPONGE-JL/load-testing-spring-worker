package com.amazonaws.samples.loadtester.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class RootController {

    @RequestMapping("/")
    public String index() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return String.format("Hey I'm running in a pod %s %s", address.getHostName(), address.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Error: %s", e.toString());
        }
    }

}
