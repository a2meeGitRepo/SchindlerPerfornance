package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier_mst")
public class Supplier {

	@Id
	@Column(name = "supplier_id")
	private int supplier_id;
	private String supplier_code;
	private String supplier_name;
	private String supplier_type;
	private String supplier_contactperson;
	private String supplier_email;
	private String supplier_email2;
	private String supplier_email3;
	private String supplier_email4;
	private String supplier_email5;
	private String supplier_email6;
	private String supplier_email7;
	private String supplier_email8;
	private String supplier_email9;
	private String supplier_mobile;
	private String supplier_mobile2;
	private String supplier_mobile3;
	private String supplier_mobile4;
	private String supplier_mobile5;
	private String supplier_mobile6;
	private String supplier_mobile7;
	private String supplier_mobile8;
	private String supplier_mobile9;
	private String supplier_address;
	private String supplier_region;
	private int deletes;

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_code() {
		return supplier_code;
	}

	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_type() {
		return supplier_type;
	}

	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}

	public String getSupplier_email() {
		return supplier_email;
	}

	public void setSupplier_email(String supplier_email) {
		this.supplier_email = supplier_email;
	}

	public String getSupplier_mobile() {
		return supplier_mobile;
	}

	public void setSupplier_mobile(String supplier_mobile) {
		this.supplier_mobile = supplier_mobile;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public String getSupplier_email2() {
		return supplier_email2;
	}

	public void setSupplier_email2(String supplier_email2) {
		this.supplier_email2 = supplier_email2;
	}

	public String getSupplier_email3() {
		return supplier_email3;
	}

	public void setSupplier_email3(String supplier_email3) {
		this.supplier_email3 = supplier_email3;
	}

	public String getSupplier_mobile2() {
		return supplier_mobile2;
	}

	public void setSupplier_mobile2(String supplier_mobile2) {
		this.supplier_mobile2 = supplier_mobile2;
	}

	public String getSupplier_mobile3() {
		return supplier_mobile3;
	}

	public void setSupplier_mobile3(String supplier_mobile3) {
		this.supplier_mobile3 = supplier_mobile3;
	}

	public String getSupplier_region() {
		return supplier_region;
	}

	public void setSupplier_region(String supplier_region) {
		this.supplier_region = supplier_region;
	}

	public String getSupplier_email4() {
		return supplier_email4;
	}

	public void setSupplier_email4(String supplier_email4) {
		this.supplier_email4 = supplier_email4;
	}

	public String getSupplier_email5() {
		return supplier_email5;
	}

	public void setSupplier_email5(String supplier_email5) {
		this.supplier_email5 = supplier_email5;
	}

	public String getSupplier_email6() {
		return supplier_email6;
	}

	public void setSupplier_email6(String supplier_email6) {
		this.supplier_email6 = supplier_email6;
	}

	public String getSupplier_mobile4() {
		return supplier_mobile4;
	}

	public void setSupplier_mobile4(String supplier_mobile4) {
		this.supplier_mobile4 = supplier_mobile4;
	}

	public String getSupplier_mobile5() {
		return supplier_mobile5;
	}

	public void setSupplier_mobile5(String supplier_mobile5) {
		this.supplier_mobile5 = supplier_mobile5;
	}

	public String getSupplier_mobile6() {
		return supplier_mobile6;
	}

	public void setSupplier_mobile6(String supplier_mobile6) {
		this.supplier_mobile6 = supplier_mobile6;
	}

	public String getSupplier_contactperson() {
		return supplier_contactperson;
	}

	public void setSupplier_contactperson(String supplier_contactperson) {
		this.supplier_contactperson = supplier_contactperson;
	}

	public String getSupplier_email7() {
		return supplier_email7;
	}

	public void setSupplier_email7(String supplier_email7) {
		this.supplier_email7 = supplier_email7;
	}

	public String getSupplier_email8() {
		return supplier_email8;
	}

	public void setSupplier_email8(String supplier_email8) {
		this.supplier_email8 = supplier_email8;
	}

	public String getSupplier_mobile7() {
		return supplier_mobile7;
	}

	public void setSupplier_mobile7(String supplier_mobile7) {
		this.supplier_mobile7 = supplier_mobile7;
	}

	public String getSupplier_mobile8() {
		return supplier_mobile8;
	}

	public void setSupplier_mobile8(String supplier_mobile8) {
		this.supplier_mobile8 = supplier_mobile8;
	}

	public String getSupplier_email9() {
		return supplier_email9;
	}

	public void setSupplier_email9(String supplier_email9) {
		this.supplier_email9 = supplier_email9;
	}

	public String getSupplier_mobile9() {
		return supplier_mobile9;
	}

	public void setSupplier_mobile9(String supplier_mobile9) {
		this.supplier_mobile9 = supplier_mobile9;
	}

	
}
