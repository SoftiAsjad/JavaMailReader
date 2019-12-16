package com.javamail.example.send_init_mails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailsController {

    @GetMapping("/sendinitmails")
    public String send() {
        new SendTestMail().sendMail();
        return "Valmis!";
    }
}
