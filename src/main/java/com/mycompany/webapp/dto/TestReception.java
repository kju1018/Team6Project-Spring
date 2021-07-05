package com.mycompany.webapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TestReception {
	private int testreceptionid;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date testdate;
	private String status;
	private int patientid;
	public int getTestreceptionid() {
		return testreceptionid;
	}
	public void setTestreceptionid(int testreceptionid) {
		this.testreceptionid = testreceptionid;
	}
	public Date getTestdate() {
		return testdate;
	}
	public void setTestdate(Date testdate) {
		this.testdate = testdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
}
