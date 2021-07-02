package com.mycompany.webapp.dto;

public class User {
	private String uid;
	private String uname;
	private String upassword;
	private String uauthority;
	private int uenabled;
	
	
	public String getUauthority() {
		return uauthority;
	}
	public void setUauthority(String uauthority) {
		this.uauthority = uauthority;
	}
	public int getUenabled() {
		return uenabled;
	}
	public void setUenabled(int uenabled) {
		this.uenabled = uenabled;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String userpassword) {
		this.upassword = userpassword;
	}
}
