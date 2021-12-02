package com.schindlerperformance.model;
import javax.persistence.Transient;

public class VPResponse {

	private int success;
	private String message;
	private Object fpy;
	private Object otd;
	private Object ppm;

	public VPResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VPResponse(int success, String message, Object fpy, Object otd, Object ppm) {
		super();
		this.success = success;
		this.message = message;
		this.fpy = fpy;
		this.otd = otd;
		this.ppm = ppm;
	}
	
	
	
	@Transient
	private String mail_id;
	
	@Transient
	private String mail_id2;
	
	@Transient
	private String mail_id3;
	
	@Transient
	private String mail_id4;
	
	@Transient
	private String mail_id5;
	
	@Transient
	private String mail_id6;
	
	@Transient
	private String mail_id7;
	
	@Transient
	private String mail_id8;
	
	@Transient
	private String mail_id9;
	
	@Transient
	private String file_name;
	
	
	private String vendor_name;

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getMail_id() {
		return mail_id;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public String getMail_id2() {
		return mail_id2;
	}

	public void setMail_id2(String mail_id2) {
		this.mail_id2 = mail_id2;
	}

	public String getMail_id3() {
		return mail_id3;
	}

	public void setMail_id3(String mail_id3) {
		this.mail_id3 = mail_id3;
	}

	public String getMail_id4() {
		return mail_id4;
	}

	public void setMail_id4(String mail_id4) {
		this.mail_id4 = mail_id4;
	}

	public String getMail_id5() {
		return mail_id5;
	}

	public void setMail_id5(String mail_id5) {
		this.mail_id5 = mail_id5;
	}

	public String getMail_id6() {
		return mail_id6;
	}

	public void setMail_id6(String mail_id6) {
		this.mail_id6 = mail_id6;
	}

	public String getMail_id7() {
		return mail_id7;
	}

	public void setMail_id7(String mail_id7) {
		this.mail_id7 = mail_id7;
	}

	public String getMail_id8() {
		return mail_id8;
	}

	public void setMail_id8(String mail_id8) {
		this.mail_id8 = mail_id8;
	}

	public String getMail_id9() {
		return mail_id9;
	}

	public void setMail_id9(String mail_id9) {
		this.mail_id9 = mail_id9;
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

	public Object getFpy() {
		return fpy;
	}

	public void setFpy(Object fpy) {
		this.fpy = fpy;
	}

	public Object getOtd() {
		return otd;
	}

	public void setOtd(Object otd) {
		this.otd = otd;
	}

	public Object getPpm() {
		return ppm;
	}

	public void setPpm(Object ppm) {
		this.ppm = ppm;
	}

	@Override
	public String toString() {
		return "VPResponse [success=" + success + ", message=" + message + ", fpy=" + fpy + ", otd=" + otd + ", ppm="
				+ ppm + "]";
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

}
