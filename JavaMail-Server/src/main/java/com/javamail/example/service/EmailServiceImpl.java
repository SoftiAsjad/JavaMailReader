package com.javamail.example.service;

import com.javamail.example.entity.EMail;
import com.javamail.example.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    MailRepository mailRepository;

    @Override
    public List<EMail> getAllEmails() {
        new EmailReceiver().downloadEmails(mailRepository);
        return mailRepository.findAll().stream().collect(Collectors.toList());
    }


}
