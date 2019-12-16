package com.javamail.example.controller;

import com.javamail.example.service.EmailService;
import com.javamail.example.service.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

import static com.javamail.example.utils.Constants.*;

@RestController
@CrossOrigin(origins = CORS_ORIGINS)
public class JavaMailController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmailService emailService;
    @Autowired
    MessagesService messagesService;

    @GetMapping(GET_TRANSLATIONS_MAPPING)
    public Map<String, String> getTranslations() {
        return messagesService.getMessagesBundle();
    }

    @GetMapping(GET_MAIL_MAPPING)
    public Collection getMail() {
        return emailService.getAllEmails();
    }

    @GetMapping("/*")
    public String err() {
        return "Bad url, error 404";
    }
}
