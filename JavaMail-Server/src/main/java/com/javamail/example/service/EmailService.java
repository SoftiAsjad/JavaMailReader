package com.javamail.example.service;


import com.javamail.example.entity.EMail;

import java.util.List;

public interface EmailService {
    public abstract List<EMail> getAllEmails();
}
