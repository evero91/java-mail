/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailsender;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTLS {

    private final Properties prop = new Properties();
    private final String username;
    private final String password;
    private final List<String> destinations;
    private final String subject;
    private final String mailBody;

    /**
     *
     * @param username
     * @param password
     * @param host
     * @param port
     * @param subject
     * @param mailBody
     * @param destinations
     */
    public EmailTLS(String username, String password, String host, String port, String subject, String mailBody, List<String> destinations) {
        this.username = username;
        this.password = password;
        this.destinations = destinations;
        this.subject = subject;
        this.mailBody = mailBody;
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
    }

    public boolean sendMail() {
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(String.join(",", destinations)));
            message.setSubject(subject);
            message.setText(mailBody);
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
        return false;
    }

}
