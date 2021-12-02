package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "performance_mst")
public class Performance {

	@Id
	@Column(name = "performance_id")
	private int performance_id;
	private String performance_suppliercode;
	private String performance_suppliername;
	private String performance_suppliertype;
	private String performance_otd;
	private String performance_fpy;
	private String performance_ppm;
	private String performance_year;
	private String performance_pdf;
	private int deletes;

	@Transient
	private Ppm ppm;
	
	@Transient
	private Otd otd;
	
	@Transient
	private Fpy fpy;
	
	
	@Transient
	private String mobile_no;

	@Transient
	private String mobile_no2;

	@Transient
	private String mobile_no3;

	@Transient
	private String mobile_no4;

	@Transient
	private String mobile_no5;

	@Transient
	private String mobile_no6;

	@Transient
	private String mobile_no7;

	@Transient
	private String mobile_no8;

	@Transient
	private String mobile_no9;

	
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

	public int getPerformance_id() {
		return performance_id;
	}

	public void setPerformance_id(int performance_id) {
		this.performance_id = performance_id;
	}

	public String getPerformance_suppliercode() {
		return performance_suppliercode;
	}

	public void setPerformance_suppliercode(String performance_suppliercode) {
		this.performance_suppliercode = performance_suppliercode;
	}

	public String getPerformance_suppliername() {
		return performance_suppliername;
	}

	public void setPerformance_suppliername(String performance_suppliername) {
		this.performance_suppliername = performance_suppliername;
	}

	public String getPerformance_suppliertype() {
		return performance_suppliertype;
	}

	public void setPerformance_suppliertype(String performance_suppliertype) {
		this.performance_suppliertype = performance_suppliertype;
	}

	public String getPerformance_otd() {
		return performance_otd;
	}

	public void setPerformance_otd(String performance_otd) {
		this.performance_otd = performance_otd;
	}

	public String getPerformance_fpy() {
		return performance_fpy;
	}

	public void setPerformance_fpy(String performance_fpy) {
		this.performance_fpy = performance_fpy;
	}

	public String getPerformance_ppm() {
		return performance_ppm;
	}

	public void setPerformance_ppm(String performance_ppm) {
		this.performance_ppm = performance_ppm;
	}

	public String getPerformance_year() {
		return performance_year;
	}

	public void setPerformance_year(String performance_year) {
		this.performance_year = performance_year;
	}

	public String getPerformance_pdf() {
		return performance_pdf;
	}

	public void setPerformance_pdf(String performance_pdf) {
		this.performance_pdf = performance_pdf;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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

	public String getMobile_no2() {
		return mobile_no2;
	}

	public void setMobile_no2(String mobile_no2) {
		this.mobile_no2 = mobile_no2;
	}

	public String getMobile_no3() {
		return mobile_no3;
	}

	public void setMobile_no3(String mobile_no3) {
		this.mobile_no3 = mobile_no3;
	}

	public String getMobile_no4() {
		return mobile_no4;
	}

	public void setMobile_no4(String mobile_no4) {
		this.mobile_no4 = mobile_no4;
	}

	public String getMobile_no5() {
		return mobile_no5;
	}

	public void setMobile_no5(String mobile_no5) {
		this.mobile_no5 = mobile_no5;
	}

	public String getMobile_no6() {
		return mobile_no6;
	}

	public void setMobile_no6(String mobile_no6) {
		this.mobile_no6 = mobile_no6;
	}

	public String getMobile_no7() {
		return mobile_no7;
	}

	public void setMobile_no7(String mobile_no7) {
		this.mobile_no7 = mobile_no7;
	}

	public String getMobile_no8() {
		return mobile_no8;
	}

	public void setMobile_no8(String mobile_no8) {
		this.mobile_no8 = mobile_no8;
	}

	public String getMobile_no9() {
		return mobile_no9;
	}

	public void setMobile_no9(String mobile_no9) {
		this.mobile_no9 = mobile_no9;
	}
	
	
	
	

}
