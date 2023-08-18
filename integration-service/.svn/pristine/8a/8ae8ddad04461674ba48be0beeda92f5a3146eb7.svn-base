package com.gg.gpos.integration.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gg.gpos.integration.dto.Mail;

import lombok.extern.slf4j.Slf4j;

@Service("mailService")
@Slf4j
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(Mail mail) {
		log.debug("entering 'sendMail' method...");
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			log.debug("entering send mail...");
			helper.setFrom("no-reply@gmonitor.ggg.com.vn");
			helper.setSubject(mail.getSubject());
			helper.setTo(mail.getMailTo());
			helper.setText(mail.getMailContent());
			mimeMessage.setContent(mail.getMailContent(), "text/html");
			
			mailSender.send(mimeMessage);
			log.debug("send mail done...");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
