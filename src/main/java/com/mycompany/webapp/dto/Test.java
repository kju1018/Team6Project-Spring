package com.mycompany.webapp.dto;


public class Test {
	private String status;
	private String result;
	private int testreceptionid;
	private String testdataid;
	private int reservationid;
	private int treatmentid;
	private int patientid;
	private String userid;
	
	public String getTestdataname() {
		return testdataname;
	}
	public void setTestdataname(String testdataname) {
		this.testdataname = testdataname;
	}
	public String getTestcontainer() {
		return testcontainer;
	}
	public void setTestcontainer(String testcontainer) {
		this.testcontainer = testcontainer;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getTesttype() {
		return testtype;
	}
	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getTestunit() {
		return testunit;
	}
	public void setTestunit(String testunit) {
		this.testunit = testunit;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	private String testdataname;
	private String testcontainer;
	private String groupcode;
	private String testtype;
	private String groupname;
	private String testunit;
	private double min;
	private double max;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getTestreceptionid() {
		return testreceptionid;
	}
	public void setTestreceptionid(int testreceptionid) {
		this.testreceptionid = testreceptionid;
	}
	public String getTestdataid() {
		return testdataid;
	}
	public void setTestdataid(String testdataid) {
		this.testdataid = testdataid;
	}
	public int getReservationid() {
		return reservationid;
	}
	public void setReservationid(int reservationid) {
		this.reservationid = reservationid;
	}
	public int getTreatmentid() {
		return treatmentid;
	}
	public void setTreatmentid(int treatmentid) {
		this.treatmentid = treatmentid;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Test [status=" + status + ", result=" + result + ", testreceptionid=" + testreceptionid
				+ ", testdataid=" + testdataid + ", reservationid=" + reservationid + ", treatmentid=" + treatmentid
				+ ", patientid=" + patientid + ", userid=" + userid + ", testdataname=" + testdataname
				+ ", testcontainer=" + testcontainer + ", groupcode=" + groupcode + ", testtype=" + testtype
				+ ", groupname=" + groupname + ", testunit=" + testunit + ", min=" + min + ", max=" + max + "]";
	}
	
	
}
