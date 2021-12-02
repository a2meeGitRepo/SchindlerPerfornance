package com.schindlerperformance.exceptions;

import java.sql.SQLException;

public class TechnicalException extends SQLException {

	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public TechnicalException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public TechnicalException() {
		// TODO Auto-generated constructor stub
		super();
	}

}
