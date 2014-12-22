package com.yx.online.model;

import java.util.Date;

/**
 * PatientVisitT entity. 
 */

public class PatientVisitT implements java.io.Serializable {

	// Fields

	private String visitId;
	private String visitName;
	private String visitType;
	private String patientId;
	private String cardId;
	private String state;
	private String createDate;
	private String copyFlag;
	// Constructors

	public String getCopyFlag() {
		return copyFlag;
	}

	public void setCopyFlag(String copyFlag) {
		this.copyFlag = copyFlag;
	}

	/** default constructor */
	public PatientVisitT() {
	}

	/** minimal constructor */
	public PatientVisitT(String visitId) {
		this.visitId = visitId;
	}

	/** full constructor */
	public PatientVisitT(String visitId, String visitName, String visitType,
			String patientId, String cardId, String state, String createDate) {
		this.visitId = visitId;
		this.visitName = visitName;
		this.visitType = visitType;
		this.patientId = patientId;
		this.cardId = cardId;
		this.state = state;
		this.createDate = createDate;
	}

	// Property accessors

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getVisitName() {
		return this.visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public String getVisitType() {
		return this.visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}