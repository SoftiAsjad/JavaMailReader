package com.javamail.example.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessagesServiceImpl implements MessagesService{

    public Map<String, String> getMessagesBundle(){
        return new MessageMap().getMessagesBundle();
    }
}
