package com.mycompany.webapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Treatment {

	private int treatmentid;
	private String memo;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date treatmentdate;
	private int patientid;
	private String userid;
	private String status;
	public int getTreatmentid() {
		return treatmentid;
	}
	public void setTreatmentid(int treatmentid) {
		this.treatmentid = treatmentid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getTreatmentdate() {
		return treatmentdate;
	}
	public void setTreatmentdate(Date treatmentdate) {
		this.treatmentdate = treatmentdate;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Treatment [treatmentid=" + treatmentid + ", memo=" + memo + ", treatmentdate=" + treatmentdate
				+ ", patientid=" + patientid + ", userid=" + userid + ", status=" + status + "]";
	}
	
	
	
	
}
