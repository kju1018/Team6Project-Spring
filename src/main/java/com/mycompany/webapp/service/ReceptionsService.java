package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ReceptionsDao;
import com.mycompany.webapp.dto.ReceptedTestDataParameter;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Treatment;


@Service
public class ReceptionsService {
	@Autowired
	private ReceptionsDao receptionsDao;
	
	//특정진료에 처방된 모든 검사데이터 아이디 가져오기(접수안된, 접수된 검사데이터 모두 포함)
	public List<String> GetPrescriptionTestDataByTreatmentid(int treatmentid) {
		List<String> list = receptionsDao.selectAllPrescriptionTestByTreatmentid(treatmentid);
		return list;
	}
	
	//특정 환자에 처방된 모든 검사데이터 아이디 가져오기(아직 접수안된 검사데이터만)
	public List<String> GetPrescriptionTestDataByPatientid(int patientid) {
		List<String> list = receptionsDao.selectAllPrescriptionTestByPatientid(patientid);
		return list;
	}
	
	
	//특정 검사데이터가져오기
	public TestData GetTestData(String testdataid ) {
		Map<String,TestData> map = new HashMap<String,TestData>();
		TestData testdata = receptionsDao.selectTestDataByTestDataid(testdataid);
		return testdata;
	}
	
	//오늘날짜의 검사데이터가져오기
	public List<Treatment> GetTreatmentData() {
		List<Treatment> list= receptionsDao.selectAllTreatment();
		return list;
	}
	
	//검사접수데이터 인서트
	public int inserTtestReception(TestReception testreception) {
		int row = receptionsDao.inserTtestReception(testreception);
		return row;
	}
	//검사접수 데이터 삭제
		public int RemoveTestReception(int testreceptionid) {
			return receptionsDao.deleteByTestreceptionid(testreceptionid); 
		}
	//테스트 접수됬을때 테스트 데이터 업데이트
	public int UpdateTestList(ReceptedTestDataParameter receptedparameter) {
		int row = receptionsDao.updateTestListByparametermap(receptedparameter);
		return row;
	}
	//전체 접수된 검사데이터 가져오기
	public List<TestReception> GetTestReceptionData() {
		
		return receptionsDao.selectAllTestReception();
	}
	

}
