package com.savin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/email")
    public String sendSimpleEmail(@RequestParam String mail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail);
        message.setSubject("Тестирование почты");
        message.setText("Привет! Это тест почты!");

        this.javaMailSender.send(message);

        return "Сообщение доставлено!";
    }
}
