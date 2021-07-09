package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class TestImg {
	private int imgid;
	private MultipartFile tattach;
	private String oname;
	private String sname;
	private String itype;
	private String testdataid;
	private int treatmentid;
	
	public int getImgid() {
		return imgid;
	}
	public void setImgid(int imgid) {
		this.imgid = imgid;
	}
	public MultipartFile getTattach() {
		return tattach;
	}
	public void setTattach(MultipartFile tattach) {
		this.tattach = tattach;
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
