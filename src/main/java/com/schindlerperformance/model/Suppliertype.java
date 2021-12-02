package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suppliertype_mst")
public class Suppliertype {

	@Id
	@Column(name = "suppliertype_id")
	private int suppliertype_id;
	private String suppliertype_type;
	private int deletes;

	public int getSuppliertype_id() {
		return suppliertype_id;
	}

	public void setSuppliertype_id(int suppliertype_id) {
		this.suppliertype_id = suppliertype_id;
	}

	public String getSuppliertype_type() {
		return suppliertype_type;
	}

	public void setSuppliertype_type(String suppliertype_type) {
		this.suppliertype_type = suppliertype_type;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

}
