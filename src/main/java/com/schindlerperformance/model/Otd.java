package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otd_mst")
public class Otd {

	@Id
	@Column(name = "otd_id")
	private int otd_id;
	private String otd_suppliercode;
	private String otd_suppliername;
	private String otd_suppliertype;
	private String otd_year;
	private String otd_jan;
	private String otd_feb;
	private String otd_mar;
	private String otd_apr;
	private String otd_may;
	private String otd_june;
	private String otd_july;
	private String otd_aug;
	private String otd_sep;
	private String otd_oct;
	private String otd_nov;
	private String otd_dec;
	private Double otd_ytd;
	private int deletes;

	public int getOtd_id() {
		return otd_id;
	}

	public void setOtd_id(int otd_id) {
		this.otd_id = otd_id;
	}

	public String getOtd_suppliercode() {
		return otd_suppliercode;
	}

	public void setOtd_suppliercode(String otd_suppliercode) {
		this.otd_suppliercode = otd_suppliercode;
	}

	public String getOtd_suppliername() {
		return otd_suppliername;
	}

	public void setOtd_suppliername(String otd_suppliername) {
		this.otd_suppliername = otd_suppliername;
	}

	public String getOtd_suppliertype() {
		return otd_suppliertype;
	}

	public void setOtd_suppliertype(String otd_suppliertype) {
		this.otd_suppliertype = otd_suppliertype;
	}

	public String getOtd_year() {
		return otd_year;
	}

	public void setOtd_year(String otd_year) {
		this.otd_year = otd_year;
	}

	public String getOtd_jan() {
		return otd_jan;
	}

	public void setOtd_jan(String otd_jan) {
		this.otd_jan = otd_jan;
	}

	public String getOtd_feb() {
		return otd_feb;
	}

	public void setOtd_feb(String otd_feb) {
		this.otd_feb = otd_feb;
	}

	public String getOtd_mar() {
		return otd_mar;
	}

	public void setOtd_mar(String otd_mar) {
		this.otd_mar = otd_mar;
	}

	public String getOtd_apr() {
		return otd_apr;
	}

	public void setOtd_apr(String otd_apr) {
		this.otd_apr = otd_apr;
	}

	public String getOtd_may() {
		return otd_may;
	}

	public void setOtd_may(String otd_may) {
		this.otd_may = otd_may;
	}

	public String getOtd_june() {
		return otd_june;
	}

	public void setOtd_june(String otd_june) {
		this.otd_june = otd_june;
	}

	public String getOtd_july() {
		return otd_july;
	}

	public void setOtd_july(String otd_july) {
		this.otd_july = otd_july;
	}

	public String getOtd_aug() {
		return otd_aug;
	}

	public void setOtd_aug(String otd_aug) {
		this.otd_aug = otd_aug;
	}

	public String getOtd_sep() {
		return otd_sep;
	}

	public void setOtd_sep(String otd_sep) {
		this.otd_sep = otd_sep;
	}

	public String getOtd_oct() {
		return otd_oct;
	}

	public void setOtd_oct(String otd_oct) {
		this.otd_oct = otd_oct;
	}

	public String getOtd_nov() {
		return otd_nov;
	}

	public void setOtd_nov(String otd_nov) {
		this.otd_nov = otd_nov;
	}

	public String getOtd_dec() {
		return otd_dec;
	}

	public void setOtd_dec(String otd_dec) {
		this.otd_dec = otd_dec;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public Double getOtd_ytd() {
		return otd_ytd;
	}

	public void setOtd_ytd(Double otd_ytd) {
		this.otd_ytd = otd_ytd;
	}

}
