package com.schindlerperformance.exceptions;

public class BusinessException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public BusinessException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public BusinessException() {
		// TODO Auto-generated constructor stub
		 super();
	}

}
