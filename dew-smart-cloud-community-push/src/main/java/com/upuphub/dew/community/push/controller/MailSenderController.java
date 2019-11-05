package com.upuphub.dew.community.push.controller;


import com.upuphub.dew.community.push.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MailSenderController {
    private final
    MailService mailService;

    public MailSenderController(MailService mailService) {
        this.mailService = mailService;
    }



    @PostMapping("/sayHello")
    public String hello(@RequestParam String hello) {
        return "Hello " + hello;
    }
}
