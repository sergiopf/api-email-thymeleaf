package com.emailthymeleaf.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class TemplateConfig {

    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
    public static final String URL_TEMPLATES = "/templates/";
    public static final String HTML_EXTENSION = ".html";
    
    /**
     *  THYMELEAF TemplateResolver(3) <- TemplateEngine                               
     */
    @Bean(name = "htmlTemplateEngine")
    public TemplateEngine htmlTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(templateMessageSource());
        return templateEngine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setPrefix(URL_TEMPLATES);
        templateResolver.setSuffix(HTML_EXTENSION);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public ResourceBundleMessageSource templateMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("templates/i18n/Template");
        return messageSource;
    } 
}
