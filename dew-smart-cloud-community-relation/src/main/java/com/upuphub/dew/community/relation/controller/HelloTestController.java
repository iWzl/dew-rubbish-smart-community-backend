package com.upuphub.dew.community.relation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloTestController {

    @GetMapping("/say ")
    public String helloWorld(){
        return "Hello World";
    }
}
