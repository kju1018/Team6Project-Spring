package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.DrugsDao;
import com.mycompany.webapp.dto.Drug;

@Service
public class DrugsService {
	
	@Autowired
	private DrugsDao drugsDao;

	public List<Drug> getStaticDrugs(){
		
		return drugsDao.selectAll();
	}
	
	public List<Drug> getDrugsByTreatmentId(int treatmentid) {
		
		return drugsDao.selectByTreatmentId(treatmentid);
	}
	
	public int insertDrugList(List<Drug> drugList) {
		int row=0;
		if(drugList.size()>0) {
			row = drugsDao.insertList(drugList);
		}
		return row;
	}
}
