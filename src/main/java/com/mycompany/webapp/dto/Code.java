package com.mycompany.webapp.dto;

public class Code {
	private int codenumber;
	private String password;
	private String hospitalname;
	public int getCodenumber() {
		return codenumber;
	}
	public void setCodenumber(int codenumber) {
		this.codenumber = codenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	@Override
	public String toString() {
		return "Code [codenumber=" + codenumber + ", password=" + password + ", hospitalname=" + hospitalname + "]";
	}
	
	
}
