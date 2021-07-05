package com.mycompany.webapp.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Schedule {
	private int scheduleid;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	private String userid;
	
	public int getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}	
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}