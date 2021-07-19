package com.mycompany.webapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Patient {
	int patientid;
	String patientname;
	String ssn1;
	String ssn2;
	String sex;
	int age;
	String phonenumber;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	Date lasttreatment;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	Date registerday;
	int codenumber;
	int enabled;
	
	public int getCodenumber() {
		return codenumber;
	}
	public void setCodenumber(int codenumber) {
		this.codenumber = codenumber;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getSsn1() {
		return ssn1;
	}
	public void setSsn1(String ssn1) {
		this.ssn1 = ssn1;
	}
	public String getSsn2() {
		return ssn2;
	}
	public void setSsn2(String ssn2) {
		this.ssn2 = ssn2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getLasttreatment() {
		return lasttreatment;
	}
	public void setLasttreatment(Date lasttreatment) {
		this.lasttreatment = lasttreatment;
	}
	public Date getRegisterday() {
		return registerday;
	}
	public void setRegisterday(Date registerday) {
		this.registerday = registerday;
	}
	
	@Override
	public String toString() {
		return "Patient [patientid=" + patientid + ", patientname=" + patientname + ", ssn1=" + ssn1 + ", ssn2=" + ssn2
				+ ", sex=" + sex + ", age=" + age + ", phonenumber=" + phonenumber + ", lasttreatment=" + lasttreatment
				+ ", registerday=" + registerday + ", codenumber=" + codenumber + ", enabled=" + enabled + "]";
	}
}
