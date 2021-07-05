package com.mycompany.webapp.dto;

public class Drug {
	private String drugid;
	private String drugname;
	private String drugtype;
	private String drugunit;
	private int treatmentid;
	private int quantity;
	
	public String getDrugid() {
		return drugid;
	}


	public void setDrugid(String drugid) {
		this.drugid = drugid;
	}


	public String getDrugname() {
		return drugname;
	}


	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}


	public String getDrugtype() {
		return drugtype;
	}


	public void setDrugtype(String drugtype) {
		this.drugtype = drugtype;
	}


	public String getDrugunit() {
		return drugunit;
	}


	public void setDrugunit(String drugunit) {
		this.drugunit = drugunit;
	}


	public int getTreatmentid() {
		return treatmentid;
	}


	public void setTreatmentid(int treatmentid) {
		this.treatmentid = treatmentid;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Drug [drugid=" + drugid + ", drugname=" + drugname + ", drugtype=" + drugtype + ", drugunit=" + drugunit
				+ ", treatmentid=" + treatmentid + ", quantity=" + quantity + "]";
	}

	
	
	
	
}
