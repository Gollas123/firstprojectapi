package com.myfirstspringproject.firstspringrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class firstController {
    
    @GetMapping("/health")
    public String first_api(){

        return "Running...........";
    }
}
