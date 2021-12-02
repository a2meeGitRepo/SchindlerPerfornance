package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fpy_mst")
public class Fpy {

	@Id
	@Column(name = "fpy_id")
	private int fpy_id;
	private String fpy_suppliercode;
	private String fpy_suppliername;
	private String fpy_suppliertype;
	private String fpy_year;
	private String fpy_jan;
	private String fpy_feb;
	private String fpy_mar;
	private String fpy_apr;
	private String fpy_may;
	private String fpy_june;
	private String fpy_july;
	private String fpy_aug;
	private String fpy_sep;
	private String fpy_oct;
	private String fpy_nov;
	private String fpy_dec;
	private double fpy_ytd;
	private int deletes;

	public int getFpy_id() {
		return fpy_id;
	}

	public void setFpy_id(int fpy_id) {
		this.fpy_id = fpy_id;
	}

	public String getFpy_suppliercode() {
		return fpy_suppliercode;
	}

	public void setFpy_suppliercode(String fpy_suppliercode) {
		this.fpy_suppliercode = fpy_suppliercode;
	}

	public String getFpy_suppliername() {
		return fpy_suppliername;
	}

	public void setFpy_suppliername(String fpy_suppliername) {
		this.fpy_suppliername = fpy_suppliername;
	}

	public String getFpy_jan() {
		return fpy_jan;
	}

	public void setFpy_jan(String fpy_jan) {
		this.fpy_jan = fpy_jan;
	}

	public String getFpy_feb() {
		return fpy_feb;
	}

	public void setFpy_feb(String fpy_feb) {
		this.fpy_feb = fpy_feb;
	}

	public String getFpy_mar() {
		return fpy_mar;
	}

	public void setFpy_mar(String fpy_mar) {
		this.fpy_mar = fpy_mar;
	}

	public String getFpy_apr() {
		return fpy_apr;
	}

	public void setFpy_apr(String fpy_apr) {
		this.fpy_apr = fpy_apr;
	}

	public String getFpy_may() {
		return fpy_may;
	}

	public void setFpy_may(String fpy_may) {
		this.fpy_may = fpy_may;
	}

	public String getFpy_june() {
		return fpy_june;
	}

	public void setFpy_june(String fpy_june) {
		this.fpy_june = fpy_june;
	}

	public String getFpy_july() {
		return fpy_july;
	}

	public void setFpy_july(String fpy_july) {
		this.fpy_july = fpy_july;
	}

	public String getFpy_aug() {
		return fpy_aug;
	}

	public void setFpy_aug(String fpy_aug) {
		this.fpy_aug = fpy_aug;
	}

	public String getFpy_sep() {
		return fpy_sep;
	}

	public void setFpy_sep(String fpy_sep) {
		this.fpy_sep = fpy_sep;
	}

	public String getFpy_oct() {
		return fpy_oct;
	}

	public void setFpy_oct(String fpy_oct) {
		this.fpy_oct = fpy_oct;
	}

	public String getFpy_nov() {
		return fpy_nov;
	}

	public void setFpy_nov(String fpy_nov) {
		this.fpy_nov = fpy_nov;
	}

	public String getFpy_dec() {
		return fpy_dec;
	}

	public void setFpy_dec(String fpy_dec) {
		this.fpy_dec = fpy_dec;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getFpy_suppliertype() {
		return fpy_suppliertype;
	}

	public void setFpy_suppliertype(String fpy_suppliertype) {
		this.fpy_suppliertype = fpy_suppliertype;
	}

	public String getFpy_year() {
		return fpy_year;
	}

	public void setFpy_year(String fpy_year) {
		this.fpy_year = fpy_year;
	}

	public double getFpy_ytd() {
		return fpy_ytd;
	}

	public void setFpy_ytd(double fpy_ytd) {
		this.fpy_ytd = fpy_ytd;
	}

}
