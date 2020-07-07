package com.charter.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Request Validation Failed")
public class ValidationException extends RuntimeException{

	public ValidationException(String msg) {
		super(msg);
	}
	
	public HttpStatus getHttpStatusForException() {
		return HttpStatus.BAD_REQUEST;
	}
	
}
