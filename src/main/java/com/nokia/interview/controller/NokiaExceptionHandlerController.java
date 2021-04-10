package com.nokia.interview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NokiaExceptionHandlerController {

	@ExceptionHandler({ConstraintViolationException.class })
	public ModelAndView handleError(final HttpServletRequest req,final ConstraintViolationException ex) {
		
		String responseMessage = "";
		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
			responseMessage += constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessage()+"/n";
		}
		
	    final ModelAndView mav = new ModelAndView();
	    mav.addObject("errorMessage", responseMessage);
	    return mav;
		
	}
}
