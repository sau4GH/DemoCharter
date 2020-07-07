package com.charter.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.charter.demo.dto.ApiResponse;
import com.charter.demo.dto.ApiResponse.Status;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
        Exception ex, WebRequest request) {

    	ApiResponse body = prepareResponse(Status.ERROR, ex.getMessage(), null);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(
    		ValidationException ex, WebRequest request) {

    	ApiResponse body = prepareResponse(Status.VALIDATION_FAILED, ex.getMessage(), null);

        return new ResponseEntity<>(body, ex.getHttpStatusForException());
    }
    
	private ApiResponse prepareResponse (Status status, String message, Object responseData ){
		
		ApiResponse response = new ApiResponse();
		
		response.setStatus(status);
		response.setMessage(message);
		response.setData(responseData);
		
		return response;
	}

}
