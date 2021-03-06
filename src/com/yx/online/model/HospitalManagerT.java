package com.yx.online.model;

import java.util.Date;

/**
 * HospitalManagerT entity. @author MyEclipse Persistence Tools
 */

public class HospitalManagerT implements java.io.Serializable {

	// Fields

	private String managerId;
	private String hospitalId;
	private String name;
	private String password;
	private Date createDate;
	private String state;
	private String privs;
	private String doctorId;

	// Constructors

	/** default constructor */
	public HospitalManagerT() {
	}

	/** minimal constructor */
	public HospitalManagerT(String managerId, String hospitalId, String name,
			String password) {
		this.managerId = managerId;
		this.hospitalId = hospitalId;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public HospitalManagerT(String managerId, String hospitalId, String name,
			String password, Date createDate, String state, String privs,
			String doctorId) {
		this.managerId = managerId;
		this.hospitalId = hospitalId;
		this.name = name;
		this.password = password;
		this.createDate = createDate;
		this.state = state;
		this.privs = privs;
		this.doctorId = doctorId;
	}

	// Property accessors

	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPrivs() {
		return this.privs;
	}

	public void setPrivs(String privs) {
		this.privs = privs;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

}