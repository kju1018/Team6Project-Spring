package com.mycompany.webapp.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice {
	private int noticeid;
	private String title;
	private String content;
	private String uid;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	public int getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUid() {
		return uid; 
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}