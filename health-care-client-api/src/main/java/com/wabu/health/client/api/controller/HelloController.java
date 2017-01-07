package com.wabu.health.client.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class HelloController {
	
    @RequestMapping(value = "/")
    public String index() {
        //return "Greetings from Spring Boot 中文 !";
    	return "test";
    }
    
}
