package com.prealkemy.disney.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class NotificationService {

    @Autowired
    public JavaMailSender mailSender;

    @Async // EJECUTA UN HILO PARALELO
    public void send(String body, String title, String mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);
        msg.setFrom("noreply@disney.com");
        msg.setSubject(title);
        msg.setText(body);

        mailSender.send(msg);
    }
}
