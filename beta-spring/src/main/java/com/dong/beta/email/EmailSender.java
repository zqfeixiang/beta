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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

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

            putZippedAttachment(articleList, helper);

            File file = AttachmentGenerator.getAttachFile("article", articleList);
            putAttachment(file, helper);

            javaMailSender.send(mimeMessage);
            log.info("Send email success!");
        } catch (MessagingException | IOException e) {
            log.error("Send email error:{0}", e);
        }
    }

    private void putAttachment(File file, MimeMessageHelper helper) throws MessagingException {
        helper.addAttachment("article.txt", file);
    }

    private void putZippedAttachment(List<Article> articleList, MimeMessageHelper helper) throws IOException, MessagingException {
        byte[] bytes = AttachmentGenerator.getAttachmentByte(articleList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        AttachmentGenerator.putByteDataToStream(bytes, zipOutputStream, "article.txt");

        InputStream in = new ByteArrayInputStream(outputStream.toByteArray());
        DataSource dataSource = new ByteArrayDataSource(in, "application/zip");

        helper.addAttachment("article.zip", dataSource);
    }
}
