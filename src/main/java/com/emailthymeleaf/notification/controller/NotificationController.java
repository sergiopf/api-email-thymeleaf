package com.emailthymeleaf.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emailthymeleaf.notification.dto.EmailDTO;
import com.emailthymeleaf.notification.service.EmailClientService;

@Controller
@RequestMapping(path = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
	
    @Autowired
    private EmailClientService sendEmail;
   
	@PostMapping("/email")
	public @ResponseBody ResponseEntity<HttpStatus> sendTemplate(@Validated @RequestBody EmailDTO request){ 
		this.sendEmail.sendEmailWithTemplate(request);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
}

