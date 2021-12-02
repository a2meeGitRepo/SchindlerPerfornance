package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplierstatus_mst")
public class Supplierstatus {

	@Id
	@Column(name = "supplierstatus_id")
	private int supplierstatus_id;
	private String supplierstatus_suppliertype;
	private String supplierstatus_suppliername;
	private String 	supplierstatus_suppliercode;
	private String supplierstatus_otd;
	private String supplierstatus_ppm;
	private String supplierstatus_fpy;
	private String supplierstatus_year;
	private int deletes;

	public int getSupplierstatus_id() {
		return supplierstatus_id;
	}

	public void setSupplierstatus_id(int supplierstatus_id) {
		this.supplierstatus_id = supplierstatus_id;
	}

	public String getSupplierstatus_suppliertype() {
		return supplierstatus_suppliertype;
	}

	public void setSupplierstatus_suppliertype(String supplierstatus_suppliertype) {
		this.supplierstatus_suppliertype = supplierstatus_suppliertype;
	}

	public String getSupplierstatus_suppliername() {
		return supplierstatus_suppliername;
	}

	public void setSupplierstatus_suppliername(String supplierstatus_suppliername) {
		this.supplierstatus_suppliername = supplierstatus_suppliername;
	}

	public String getSupplierstatus_otd() {
		return supplierstatus_otd;
	}

	public void setSupplierstatus_otd(String supplierstatus_otd) {
		this.supplierstatus_otd = supplierstatus_otd;
	}

	public String getSupplierstatus_ppm() {
		return supplierstatus_ppm;
	}

	public void setSupplierstatus_ppm(String supplierstatus_ppm) {
		this.supplierstatus_ppm = supplierstatus_ppm;
	}

	public String getSupplierstatus_fpy() {
		return supplierstatus_fpy;
	}

	public void setSupplierstatus_fpy(String supplierstatus_fpy) {
		this.supplierstatus_fpy = supplierstatus_fpy;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getSupplierstatus_suppliercode() {
		return supplierstatus_suppliercode;
	}

	public void setSupplierstatus_suppliercode(String supplierstatus_suppliercode) {
		this.supplierstatus_suppliercode = supplierstatus_suppliercode;
	}

	public String getSupplierstatus_year() {
		return supplierstatus_year;
	}

	public void setSupplierstatus_year(String supplierstatus_year) {
		this.supplierstatus_year = supplierstatus_year;
	}
	
	

}
