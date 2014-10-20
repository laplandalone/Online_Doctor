package com.yx.online.model;

import com.google.gson.annotations.Expose;

public class User
{
	@Expose
	private String hospital_id;
	@Expose
	private String manager_id;
	@Expose
	private String name;
	@Expose
	private String doctor_id;
	
	@Expose
	private String password;
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}


}
