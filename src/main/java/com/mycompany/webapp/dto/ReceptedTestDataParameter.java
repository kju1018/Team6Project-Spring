package com.mycompany.webapp.dto;

import java.util.List;

public class ReceptedTestDataParameter {
	int testreceptionid;
	List<String> testdataidlist;
	
	public int getTestreceptionid() {
		return testreceptionid;
	}
	public void setTestreceptionid(int testreceptionid) {
		this.testreceptionid = testreceptionid;
	}
	public List<String> getTestdataidlist() {
		return testdataidlist;
	}
	public void setTestdataidlist(List<String> testdataidlist) {
		this.testdataidlist = testdataidlist;
	}
}
