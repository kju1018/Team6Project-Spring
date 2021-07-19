package com.mycompany.webapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChattingHistory {
	int chatid;
	String userid;
	String history;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	Date timestamp;

	public int getChatid() {
		return chatid;
	}
	public void setChatid(int chatid) {
		this.chatid = chatid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
