package com.emailthymeleaf.notification.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler
 *
 */
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
 
	private static final Logger log = LogManager.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler({ RuntimeException.class })
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
    	log.error(ex);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	log.error(ex);
    	return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	log.error(ex);
    	return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}