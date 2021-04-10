package com.nokia.interview.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NokiaExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<?> handleConstraintViolation(Exception ex, WebRequest request) {
	    Throwable cause = ((TransactionSystemException) ex).getRootCause();
	    if (cause instanceof ConstraintViolationException) {
	        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
	        // do something here,
	        System.out.println("ssss");
	    }
		return null;
	}
}
