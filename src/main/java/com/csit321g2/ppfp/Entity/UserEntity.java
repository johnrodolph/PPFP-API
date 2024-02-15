package com.csit321g2.ppfp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblUser")

public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int uid;

	private String email;

	private String password;

	@Column(name = "firstname")
	private String fname;

	@Column(name = "lastname")
	private String lname;

	private String gender;

	private boolean isDeleted;

	private boolean isAdmin;

	private boolean isMod;

	private boolean isApplying;

	private boolean isApproved;

	private boolean isRejected;

	public UserEntity() {
		super();
	}

	public UserEntity(int uid, String email, String password, String fname, String lname, String gender,
			boolean isDeleted, boolean isAdmin, boolean isMod, boolean isApplying, boolean isApproved,
			boolean isRejected) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.isDeleted = isDeleted;
		this.isAdmin = isAdmin;
		this.isMod = isMod;
		this.isApplying = isApplying;
		this.isApproved = isApproved;
		this.isRejected = isRejected;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isMod() {
		return isMod;
	}

	public void setMod(boolean isMod) {
		this.isMod = isMod;
	}

	public boolean isApplying() {
		return isApplying;
	}

	public void setApplying(boolean isApplying) {
		this.isApplying = isApplying;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}