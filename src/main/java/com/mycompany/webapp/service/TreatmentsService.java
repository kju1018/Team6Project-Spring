package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TreatmentsDao;
import com.mycompany.webapp.dto.Treatment;

@Service
public class TreatmentsService {
	
	@Autowired
	private TreatmentsDao treatmentsDao;
	
	public List<Treatment> getTreatments(String patientid) {
		return treatmentsDao.selectByPatientId(patientid);
	}
	
	public Treatment create(Treatment treatment) {
		treatmentsDao.insert(treatment);
		return treatment;
	}
	
	public int deleteTreatment(int treatmentid) {
		 return treatmentsDao.delete(treatmentid);
	}
	
	public int updateStatus(Treatment treatment) {
		return treatmentsDao.update(treatment);
	}
	
}
