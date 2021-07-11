package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class TestImg {
	private int imgid;
	private String oname;
	private String sname;
	private String itype;
	private String testdataid;
	private int treatmentid;
	private MultipartFile battach;
	public MultipartFile getBattach() {
		return battach;
	}
	public void setBattach(MultipartFile battach) {
		this.battach = battach;
	}
	public int getImgid() {
		return imgid;
	}
	public void setImgid(int imgid) {
		this.imgid = imgid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getItype() {
		return itype;
	}
	public void setItype(String itype) {
		this.itype = itype;
	}
	public String getTestdataid() {
		return testdataid;
	}
	public void setTestdataid(String testdataid) {
		this.testdataid = testdataid;
	}
	public int getTreatmentid() {
		return treatmentid;
	}
	public void setTreatmentid(int treatmentid) {
		this.treatmentid = treatmentid;
	}
}
