package com.schindlerperformance.model;
public class RestResponse {

	private int success;
	private String message;
	private Object list;

	public RestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestResponse(int success, String message, Object list) {
		super();
		this.success = success;
		this.message = message;
		this.list = list;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "RestResponse [success=" + success + ", message=" + message + ", list=" + list + "]";
	}

}
