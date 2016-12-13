package com.wabu.health.business.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    //@RequestMapping(value = "/", produces = "application/json; charset=UTF-8")
    @RequestMapping(value = "/")
    public String index() {
        return "Greetings from Spring Boot 中文 9001!";
    }
    
}
