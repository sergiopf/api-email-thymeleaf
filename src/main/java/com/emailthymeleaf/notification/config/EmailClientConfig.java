package com.emailthymeleaf.notification.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail/config/emailconfig.properties")
public class EmailClientConfig implements ApplicationContextAware, EnvironmentAware {
	
    private static final String JAVA_MAIL_FILE = "classpath:mail/config/javamail.properties";
    private static final String HOST = "mail.server.host";
    private static final String PORT = "mail.server.port";
    private static final String PROTOCOL = "mail.server.protocol";
    private static final String USERNAME = "mail.server.username";
    private static final String PASSWORD = "mail.server.password";

    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    /**
     * JavaMailSender instance, configured via .properties files.
     */
    @Bean
    public JavaMailSender mailSender() throws IOException {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
        mailSender.setUsername(this.environment.getProperty(USERNAME));
        mailSender.setPassword(this.environment.getProperty(PASSWORD));
        
        // JavaMail-specific mail sender configuration, based on javamail.properties
    	/*final Properties properties = new Properties();
    	properties.put("mail.smtp.auth", Boolean.TRUE);
    	properties.put("mail.smtp.starttls.enable", Boolean.TRUE);
    	properties.put("mail.smtp.quitwait", Boolean.FALSE);
    	properties.put("mail.smtp.socketFactory.fallback", Boolean.FALSE);*/

        final Properties javaMailProperties = new Properties();
        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    
    @Bean
    public ResourceBundleMessageSource emailSubjectSource() {
       ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
       rs.setBasename("mail/Subjects");
       rs.setDefaultEncoding("UTF-8");
       rs.setUseCodeAsDefaultMessage(true);
       return rs;
    }
}
