package com.SpringProj.todo.Services.Mail;

import jakarta.mail.MessagingException;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;
}
