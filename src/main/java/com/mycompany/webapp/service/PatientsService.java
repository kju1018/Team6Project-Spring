package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.PatientsDao;
import com.mycompany.webapp.dao.ReservationsDao;
import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Reservation;


@Service
public class PatientsService {
	@Autowired
	private PatientsDao patientsDao;

	//환자등록
	public int RegisterPatient(Patient patient) {
		return patientsDao.insert(patient);
	}
	//환자수정
	public int UpdatePatient(Patient patient) {
		return patientsDao.update(patient);
	}
	//전체환자 가져오기
	public List<Patient> getPatientList(){
		return patientsDao.selectAll();
	}
	//특정환자 가져오기
	public Patient getPatient(String patientid) {
		return patientsDao.selectByPatient(patientid);
	}
}
