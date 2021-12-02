package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ppm_mst")
public class Ppm {

	@Id
	@Column(name = "ppm_id")
	private int ppm_id;
	private String ppm_suppliercode;
	private String ppm_suppliername;
	private String ppm_supplierType;
	private String ppm_year;
	private String ppm_jan;
	private String ppm_feb;
	private String ppm_mar;
	private String ppm_apr;
	private String ppm_may;
	private String ppm_june;
	private String ppm_july;
	private String ppm_aug;
	private String ppm_sep;
	private String ppm_oct;
	private String ppm_nov;
	private String ppm_dec;
	private long ppm_ytd;
	private int deletes;

	public int getPpm_id() {
		return ppm_id;
	}

	public void setPpm_id(int ppm_id) {
		this.ppm_id = ppm_id;
	}

	public String getPpm_suppliercode() {
		return ppm_suppliercode;
	}

	public void setPpm_suppliercode(String ppm_suppliercode) {
		this.ppm_suppliercode = ppm_suppliercode;
	}

	public String getPpm_suppliername() {
		return ppm_suppliername;
	}

	public void setPpm_suppliername(String ppm_suppliername) {
		this.ppm_suppliername = ppm_suppliername;
	}

	public String getPpm_jan() {
		return ppm_jan;
	}

	public void setPpm_jan(String ppm_jan) {
		this.ppm_jan = ppm_jan;
	}

	public String getPpm_feb() {
		return ppm_feb;
	}

	public void setPpm_feb(String ppm_feb) {
		this.ppm_feb = ppm_feb;
	}

	public String getPpm_mar() {
		return ppm_mar;
	}

	public void setPpm_mar(String ppm_mar) {
		this.ppm_mar = ppm_mar;
	}

	public String getPpm_apr() {
		return ppm_apr;
	}

	public void setPpm_apr(String ppm_apr) {
		this.ppm_apr = ppm_apr;
	}

	public String getPpm_may() {
		return ppm_may;
	}

	public void setPpm_may(String ppm_may) {
		this.ppm_may = ppm_may;
	}

	public String getPpm_june() {
		return ppm_june;
	}

	public void setPpm_june(String ppm_june) {
		this.ppm_june = ppm_june;
	}

	public String getPpm_july() {
		return ppm_july;
	}

	public void setPpm_july(String ppm_july) {
		this.ppm_july = ppm_july;
	}

	public String getPpm_aug() {
		return ppm_aug;
	}

	public void setPpm_aug(String ppm_aug) {
		this.ppm_aug = ppm_aug;
	}

	public String getPpm_sep() {
		return ppm_sep;
	}

	public void setPpm_sep(String ppm_sep) {
		this.ppm_sep = ppm_sep;
	}

	public String getPpm_oct() {
		return ppm_oct;
	}

	public void setPpm_oct(String ppm_oct) {
		this.ppm_oct = ppm_oct;
	}

	public String getPpm_nov() {
		return ppm_nov;
	}

	public void setPpm_nov(String ppm_nov) {
		this.ppm_nov = ppm_nov;
	}

	public String getPpm_dec() {
		return ppm_dec;
	}

	public void setPpm_dec(String ppm_dec) {
		this.ppm_dec = ppm_dec;
	}

	public long getPpm_ytd() {
		return ppm_ytd;
	}

	public void setPpm_ytd(long ppm_ytd) {
		this.ppm_ytd = ppm_ytd;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getPpm_supplierType() {
		return ppm_supplierType;
	}

	public void setPpm_supplierType(String ppm_supplierType) {
		this.ppm_supplierType = ppm_supplierType;
	}

	public String getPpm_year() {
		return ppm_year;
	}

	public void setPpm_year(String ppm_year) {
		this.ppm_year = ppm_year;
	}

}
