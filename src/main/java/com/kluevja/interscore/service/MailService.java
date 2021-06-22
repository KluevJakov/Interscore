package com.kluevja.interscore.service;


import com.kluevja.interscore.entity.UserEntity;
import com.kluevja.interscore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    public void send(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    public void activate(UserEntity u) {
        send(u.getEmail(), "Мы рады, что вы с нами", "Активируйте аккаунт по ссылке:  http://localhost:4200/activation/" + u.getActivationCode());
    }
}