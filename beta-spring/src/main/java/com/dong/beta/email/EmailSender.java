package com.dong.beta.email;

import com.dong.beta.entity.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 11/21/22 14:12
 */
@Service
@Import(EmailConfig.class)
@EnableAutoConfiguration
@Slf4j
public class EmailSender {

    private static final String SUBJECT = "New Articles";

    @Value("${app.mail.from-address:}")
    String fromAddress;

    @Value("${app.mail.to-address:}")
    String[] toAddress;

    @Value("${app.mail.cc-address:}")
    String[] ccAddress;

    private final SpringTemplateEngine emailTemplateEngine;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(SpringTemplateEngine emailTemplateEngine, JavaMailSender javaMailSender) {
        this.emailTemplateEngine = emailTemplateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(List<Article> articleList) {
        Map<String, Integer> props = new LinkedHashMap<>();
        props.put("size", articleList.size());

        Context context = new Context();
        context.setVariable("technologies", Arrays.asList("Python", "Go", "C#"));
        context.setVariable("subscriptionDate", LocalDate.now().toString());
        context.setVariable("name", "dzq");
        context.setVariable("props", props);
        context.setVariable("articles", articleList);

        String process = emailTemplateEngine.process("email-report", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(SUBJECT);
            helper.setText(process, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);

            javaMailSender.send(mimeMessage);
            log.info("Send email success!");
        } catch (MessagingException e) {
            log.error("Send email error:{0}", e);
        }
    }
}
