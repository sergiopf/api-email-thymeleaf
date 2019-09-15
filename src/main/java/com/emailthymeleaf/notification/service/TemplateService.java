package com.emailthymeleaf.notification.service;

import com.emailthymeleaf.notification.dto.EmailDTO;

public interface TemplateService {

	public String getHTMLTemplate(EmailDTO request);

}
