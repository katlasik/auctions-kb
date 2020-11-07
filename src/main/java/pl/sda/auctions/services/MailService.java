package pl.sda.auctions.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @Value("${mail.sender}")
    private String from;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    void send(String to, String subject, String text) {

        var message = new SimpleMailMessage();

        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);
        message.setFrom(from);

        log.info("Sending mail to {}.", to);

        mailSender.send(message);

    }


}
