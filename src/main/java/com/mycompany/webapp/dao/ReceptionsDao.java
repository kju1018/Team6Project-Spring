package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.ReceptedTestDataParameter;
import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Treatment;


	@Mapper
	public interface ReceptionsDao {
		public List<String> selectAllPrescriptionTestByTreatmentid(int treatmentid);
		public List<String> selectAllPrescriptionTestByPatientid(int patientid);
		public TestData selectTestDataByTestDataid(String testdataid);
		public List<Treatment> selectAllTreatment();
		public int inserTtestReception(TestReception testreception);
		public int updateTestListByparametermap(ReceptedTestDataParameter receptedparameter);
		public List<TestReception> selectAllTestReception();
	}


