package com.schindlerperformance.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_mst")
@Embeddable
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "fname")
	private String fname;

	@Column(name = "mobile_no")
	private String mobile_no;

	@Column(name = "password")
	private String password;

	private String added_by;

	private String email;

	private String user_code;

	
	
	
	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	/*
	 * public String getAdded_time() { return added_time; }
	 * 
	 * public void setAdded_time(String added_time) { this.added_time =
	 * added_time; }
	 */
	// Map one to one association between User and Role
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

//	@ManyToOne
//	@JoinColumn(name = "user_type_id")
//	private UserType userType;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}
