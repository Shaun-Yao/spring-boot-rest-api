package com.wabu.health.business.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class HelloController {
	
    //@RequestMapping(value = "/", produces = "application/json; charset=UTF-8")
    @RequestMapping(value = "/")
    public String index() {
    	return "test";
        //return "Greetings from Spring Boot 中文 9001!";
    }
    
}
