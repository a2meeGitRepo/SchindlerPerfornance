package com.schindlerperformance.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role_mst")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private int role_id;
	
	@Column(name="role_name")
	private String role_name;
	
	@Column(name="role_acess")
	private String role_acess;
	
	
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRole_acess() {
		return role_acess;
	}

	public void setRole_acess(String role_acess) {
		this.role_acess = role_acess;
	}
	
	
	
}
