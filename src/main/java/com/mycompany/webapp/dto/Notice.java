package com.mycompany.webapp.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice {
	private int noticeid;
	private String title;
	private String content;
	private String userid;
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
	public String getUserid() {
		return userid; 
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Notice [noticeid=" + noticeid + ", title=" + title + ", content=" + content + ", userid=" + userid
				+ ", date=" + date + "]";
	}
	
	
	
	
}