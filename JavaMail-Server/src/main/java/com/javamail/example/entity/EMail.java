package com.javamail.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class EMail {
    @Id @GeneratedValue
    private Long id;
    private String sender;
    private String subject;
    private String body;
    private String code;

}
