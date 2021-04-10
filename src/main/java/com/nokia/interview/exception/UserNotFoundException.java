package com.nokia.interview.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7751049695236616944L;
	
	private final Long userId;

	public UserNotFoundException(final Long userId) {
		this.userId = userId;
	}


}
