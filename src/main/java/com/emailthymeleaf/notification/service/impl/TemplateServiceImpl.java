package com.emailthymeleaf.notification.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.emailthymeleaf.notification.config.TemplateConfig;
import com.emailthymeleaf.notification.dto.EmailDTO;
import com.emailthymeleaf.notification.service.TemplateService;


@Service
public class TemplateServiceImpl implements TemplateService{
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
	private static final String TEMPLATE_WELLCOME = "wellcome"; //$NON-NLS-1$
	
	@Autowired
	TemplateEngine htmlTemplateEngine;
	
	/**
	 * Search thymeleaf file template for a passed locale, if not exists search by default locale en
	 * if exist return the template filled else null and error
	 */
	@Override
	public String getHTMLTemplate(EmailDTO request){

		logger.debug("TemplateController getHTMLTemplate: " + request.toString());
		
		String templateName = request.getTemplate() + TemplateConfig.HTML_EXTENSION;
		return this.htmlTemplateEngine.process(templateName, setTemplateContext(request));
		
	}

	/**
	 * Fill template parameters
	 */
	private static Context setTemplateContext(EmailDTO request) {

		Locale locale = new Locale(request.getLocale());
        Context ctx = new Context(locale);
        
		switch(request.getTemplate()) {
			case TEMPLATE_WELLCOME:
		        ctx.setVariable("name", request.getUserName());
		        ctx.setVariable("subscriptionDate", new Date());
		        switch(request.getLocale()) {
		        	case "es":
		        		ctx.setVariable("hobbies", Arrays.asList("Cine", "Deporte", "Musica"));
		        		break;
		        	case "en":
		        		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		        		break;
				default:
					break;
		        }
		        break;
		default:
			break;
		}
		
		logger.debug("Template locale: " + ctx.getLocale().toString() +
				" template names: " + request.getTemplate() +
				" variable names: " + ctx.getVariableNames().toString());

		return ctx;
	}
	
}
