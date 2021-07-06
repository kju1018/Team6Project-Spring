package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.DiagnosesDao;
import com.mycompany.webapp.dto.Diagnoses;
import com.mycompany.webapp.dto.Drug;

@Service
public class DiagnosesService {
	
	@Autowired
	private DiagnosesDao diagnosesDao;
	
	public List<Diagnoses> getStaticDiagnoses(){
		
		return diagnosesDao.selectAll();
	}
	
	
	public List<Diagnoses> getDiagnosesByTreatmentId(int treatmentid) {
		
		return diagnosesDao.selectByTreatmentId(treatmentid);
	}
	
	public int insertDiagnosesList(List<Diagnoses> diagnosesList) {
		int row = 0;
		if(diagnosesList.size()>0) {
			row = diagnosesDao.insertList(diagnosesList);
		}
		return row;
	}

}
