package com.emailthymeleaf.notification.service;

import com.emailthymeleaf.notification.dto.EmailDTO;

public interface EmailClientService {

	public void sendEmailWithTemplate(EmailDTO request);

}