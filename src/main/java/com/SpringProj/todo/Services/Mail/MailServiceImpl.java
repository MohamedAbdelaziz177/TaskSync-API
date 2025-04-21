package com.SpringProj.todo.Services.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mohamecabdelaziz66@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);

    }


    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("mohamecabdelaziz66@gmail.com"));
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        message.setRecipients(MimeMessage.RecipientType.TO, "mohamecabdelaziz66@gmail.com");

        mailSender.send(message);

    }
}
