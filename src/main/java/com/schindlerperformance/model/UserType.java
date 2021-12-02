package com.schindlerperformance.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_type_mst")
public class UserType implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_type_id", unique=true, nullable=false)
	private int user_type_id;
	
	@Column(name="user_type_name")
	private String user_type_name;
	
	@Column(name="active")
	private int active;
	
	

	public int getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}

	public String getUser_type_name() {
		return user_type_name;
	}

	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	
}
