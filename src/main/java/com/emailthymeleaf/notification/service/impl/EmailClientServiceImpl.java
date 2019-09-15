package com.emailthymeleaf.notification.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailthymeleaf.notification.dto.EmailDTO;
import com.emailthymeleaf.notification.service.EmailClientService;

@Service
public class EmailClientServiceImpl implements EmailClientService{

	private static final Logger logger = LoggerFactory.getLogger(EmailClientServiceImpl.class);

    private static ResourceBundleMessageSource emailSubjectSource;

    @Autowired
    private void SendEmail(ResourceBundleMessageSource emailSubjectSource) {
	    EmailClientServiceImpl.emailSubjectSource = emailSubjectSource;
    }
	
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateServiceImpl templateController;

	public void sendEmailWithTemplate(EmailDTO request){

		logger.debug("SendEmailImpl sendEmailWithTemplate init");

        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
			MimeMessageHelper message = new MimeMessageHelper(msg, true);
			message.setTo(request.getEmail());
			
			Locale locale = new Locale(request.getLocale());
			message.setSubject(emailSubjectSource.getMessage(request.getTemplate(), null, locale));
			message.setText(templateController.getHTMLTemplate(request), true);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

        javaMailSender.send(msg);
        
        //Attach file
        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        //message.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        
        logger.debug("SendEmailImpl sendEmailWithTemplate end");
        
    }
}
