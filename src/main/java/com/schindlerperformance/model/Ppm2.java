package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ppm2_mst")
public class Ppm2 {

	@Id
	@Column(name = "ppm2_id")
	private int ppm2_id;
	private String ppm2_occ;
	private String ppm2_kw;
	public int getPpm2_id() {
		return ppm2_id;
	}
	public void setPpm2_id(int ppm2_id) {
		this.ppm2_id = ppm2_id;
	}
	public String getPpm2_occ() {
		return ppm2_occ;
	}
	public void setPpm2_occ(String ppm2_occ) {
		this.ppm2_occ = ppm2_occ;
	}
	public String getPpm2_kw() {
		return ppm2_kw;
	}
	public void setPpm2_kw(String ppm2_kw) {
		this.ppm2_kw = ppm2_kw;
	}



}
