package com.mycompany.webapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reservation {
	int reservationid;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	Date reservationdate;
	String status;
	String type;
	int patientid;
	String patientname;
	
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public int getReservationid() {
		return reservationid;
	}
	public void setReservationid(int reservationid) {
		this.reservationid = reservationid;
	}
	public Date getReservationdate() {
		return reservationdate;
	}
	public void setReservationdate(Date reservationdate) {
		this.reservationdate = reservationdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
}
