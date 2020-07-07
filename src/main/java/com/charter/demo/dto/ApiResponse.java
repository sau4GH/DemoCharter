package com.charter.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {
	
	private Status status;
	private String message;
	private T data;
	
	public ApiResponse() {
		// No arg Constructor
	}
	
	public ApiResponse(Status status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ApiResponse(Status status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ApiResponse(String message, T data) {
		this.status = Status.SUCCESS;
		this.message = message;
		this.data = data;
	}
	
	public ApiResponse(T data) {
		this.status = Status.SUCCESS;
		this.message = "Completed Successfully.";
		this.data = data;
	}
	
	public enum Status {
		ERROR, SUCCESS, INFO, VALIDATION_FAILED
	}

}


