package org.oome.infra.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.infra.email.dto.EmailSendDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Component
public class MailSendService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(EmailSendDto sendDto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            /**
             * 첨부 파일(Multipartfile) 보낼거면 true
             */
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(sendDto.getTo());
            mimeMessageHelper.setSubject(sendDto.getSubject());
            /**
             * html 템플릿으로 보낼거면 true
             * plaintext로 보낼거면 false
             */
            mimeMessageHelper.setText(sendDto.getMessage(), true);

            javaMailSender.send(mimeMessage);
            log.info("sent email: {}", sendDto.getMessage());
        } catch (MessagingException e) {
            log.error("[EmailService.send()] error {}", e.getMessage());
        }
    }
}
