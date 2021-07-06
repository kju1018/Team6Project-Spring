package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.dto.TestData;


	@Mapper
	public interface ReceptionsDao {
		public List<String> selectAllPrescriptionTestByTreatmentid(int treatmentid);
		public List<String> selectAllPrescriptionTestByPatientid(int patientid);
		public TestData selectTestDataByTestDataid(String testdataid);
		
	}


