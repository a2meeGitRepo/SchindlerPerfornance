package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "escalation_mst")
public class Escalation {

	@Id
	@Column(name = "escalation_id")
	private int escalation_id;
	private String escalation_suppliercode;
	private String escalation_suppliername;
	private String escalation_suppliertype;
	private String escalation_year;
	private String escalation_warning;
	private String escalation_planreceive1;
	private String escalation_planvalidated1;
	private String escalation_level1;
	private String escalation_planreceive2;
	private String escalation_planvalidated2;
	private String escalation_deescalated;
	private String escalation_level2;
	private String escalation_discontinued;
	private String escalation_date;
	private String escalation_warningdate;
	private String escalation_planreceive1date;
	private String escalation_planvalidated1date;
	private String escalation_level1date;
	private String escalation_planreceive2date;
	private String escalation_planvalidated2date;
	private String escalation_deescalateddate;
	private String escalation_level2date;
	private int deletes;
	
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

	public String getMail_id() {
		return mail_id;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public int getEscalation_id() {
		return escalation_id;
	}

	public void setEscalation_id(int escalation_id) {
		this.escalation_id = escalation_id;
	}

	public String getEscalation_suppliercode() {
		return escalation_suppliercode;
	}

	public void setEscalation_suppliercode(String escalation_suppliercode) {
		this.escalation_suppliercode = escalation_suppliercode;
	}

	public String getEscalation_suppliername() {
		return escalation_suppliername;
	}

	public void setEscalation_suppliername(String escalation_suppliername) {
		this.escalation_suppliername = escalation_suppliername;
	}

	public String getEscalation_suppliertype() {
		return escalation_suppliertype;
	}

	public void setEscalation_suppliertype(String escalation_suppliertype) {
		this.escalation_suppliertype = escalation_suppliertype;
	}

	public String getEscalation_year() {
		return escalation_year;
	}

	public void setEscalation_year(String escalation_year) {
		this.escalation_year = escalation_year;
	}

	public String getEscalation_warning() {
		return escalation_warning;
	}

	public void setEscalation_warning(String escalation_warning) {
		this.escalation_warning = escalation_warning;
	}

	public String getEscalation_planreceive1() {
		return escalation_planreceive1;
	}

	public void setEscalation_planreceive1(String escalation_planreceive1) {
		this.escalation_planreceive1 = escalation_planreceive1;
	}

	public String getEscalation_planvalidated1() {
		return escalation_planvalidated1;
	}

	public void setEscalation_planvalidated1(String escalation_planvalidated1) {
		this.escalation_planvalidated1 = escalation_planvalidated1;
	}

	public String getEscalation_level1() {
		return escalation_level1;
	}

	public void setEscalation_level1(String escalation_level1) {
		this.escalation_level1 = escalation_level1;
	}

	public String getEscalation_planreceive2() {
		return escalation_planreceive2;
	}

	public void setEscalation_planreceive2(String escalation_planreceive2) {
		this.escalation_planreceive2 = escalation_planreceive2;
	}

	public String getEscalation_planvalidated2() {
		return escalation_planvalidated2;
	}

	public void setEscalation_planvalidated2(String escalation_planvalidated2) {
		this.escalation_planvalidated2 = escalation_planvalidated2;
	}

	public String getEscalation_deescalated() {
		return escalation_deescalated;
	}

	public void setEscalation_deescalated(String escalation_deescalated) {
		this.escalation_deescalated = escalation_deescalated;
	}

	public String getEscalation_level2() {
		return escalation_level2;
	}

	public void setEscalation_level2(String escalation_level2) {
		this.escalation_level2 = escalation_level2;
	}

	public String getEscalation_discontinued() {
		return escalation_discontinued;
	}

	public void setEscalation_discontinued(String escalation_discontinued) {
		this.escalation_discontinued = escalation_discontinued;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getEscalation_date() {
		return escalation_date;
	}

	public void setEscalation_date(String escalation_date) {
		this.escalation_date = escalation_date;
	}

	public String getEscalation_warningdate() {
		return escalation_warningdate;
	}

	public void setEscalation_warningdate(String escalation_warningdate) {
		this.escalation_warningdate = escalation_warningdate;
	}

	public String getEscalation_planreceive1date() {
		return escalation_planreceive1date;
	}

	public void setEscalation_planreceive1date(String escalation_planreceive1date) {
		this.escalation_planreceive1date = escalation_planreceive1date;
	}

	public String getEscalation_planvalidated1date() {
		return escalation_planvalidated1date;
	}

	public void setEscalation_planvalidated1date(String escalation_planvalidated1date) {
		this.escalation_planvalidated1date = escalation_planvalidated1date;
	}

	public String getEscalation_level1date() {
		return escalation_level1date;
	}

	public void setEscalation_level1date(String escalation_level1date) {
		this.escalation_level1date = escalation_level1date;
	}

	public String getEscalation_planreceive2date() {
		return escalation_planreceive2date;
	}

	public void setEscalation_planreceive2date(String escalation_planreceive2date) {
		this.escalation_planreceive2date = escalation_planreceive2date;
	}

	public String getEscalation_planvalidated2date() {
		return escalation_planvalidated2date;
	}

	public void setEscalation_planvalidated2date(String escalation_planvalidated2date) {
		this.escalation_planvalidated2date = escalation_planvalidated2date;
	}

	public String getEscalation_deescalateddate() {
		return escalation_deescalateddate;
	}

	public void setEscalation_deescalateddate(String escalation_deescalateddate) {
		this.escalation_deescalateddate = escalation_deescalateddate;
	}

	public String getEscalation_level2date() {
		return escalation_level2date;
	}

	public void setEscalation_level2date(String escalation_level2date) {
		this.escalation_level2date = escalation_level2date;
	}

	
}
