package com.emailthymeleaf.notification.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 *  @DateTimeFormat, has problem to overwrite its  fixed message (Failed to convert property value of type change)
 *  so to create personalized messages we use a ConstraintValidator, and set LocalDateTime fields 
 *	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
 * @author sergi
 *
 */
public class EmailDTO implements Serializable{

	private static final long serialVersionUID = -687991492884056763L;
	
	@NotEmpty
	@Email
	String email;

	@NotEmpty
	String locale;
	
	@NotEmpty
	String template;

	@NotEmpty
	String userName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString() {
		return "email: "+this.email+
			   " locale: "+this.locale+
			   " template: "+this.template+
			   " userName: "+this.userName;
	}
	
}
