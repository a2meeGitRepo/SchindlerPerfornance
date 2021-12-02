package com.schindlerperformance.model;
public class Status {

	
	private String message;

	public Status() {
	}

	public Status(String message) {
		
		this.message = message;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
