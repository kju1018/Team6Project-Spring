package com.mycompany.webapp.dto;

public class User {
	private String userid;
	private String username;
	private String userroom;
	private String role_authority;
	private String userpassword;
	private String phonenumber;
	private int userenabled;
	private int codenumber;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserroom() {
		return userroom;
	}
	public void setUserroom(String userroom) {
		this.userroom = userroom;
	}
	public String getRole_authority() {
		return role_authority;
	}
	public void setRole_authority(String role_authority) {
		this.role_authority = role_authority;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getUserenabled() {
		return userenabled;
	}
	public void setUserenabled(int userenabled) {
		this.userenabled = userenabled;
	}
	public int getCodenumber() {
		return codenumber;
	}
	public void setCodenumber(int codenumber) {
		this.codenumber = codenumber;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", userroom=" + userroom + ", role_authority="
				+ role_authority + ", userpassword=" + userpassword + ", phonenumber=" + phonenumber + ", userenabled="
				+ userenabled + ", codenumber=" + codenumber + "]";
	}
}
