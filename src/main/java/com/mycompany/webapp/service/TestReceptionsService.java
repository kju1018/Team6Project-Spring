package com.mycompany.webapp.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestReceptionsDao;
import com.mycompany.webapp.dto.TestReception;

@Service
public class TestReceptionsService {
	private static final Logger logger = 
			LoggerFactory.getLogger(TestsService.class);
	@Autowired
	private TestReceptionsDao testreceptionsDao;
	
	public List<TestReception> getTestReceptionListByPatientId(int patientid) {
		List<TestReception> list = testreceptionsDao.selectbyPatientid(patientid);

		return list;
	}

	public List<TestReception> getTestReceptionListByDate(String startdate, String enddate) {
		List<TestReception> list = testreceptionsDao.selectbyTestdate(startdate, enddate);

		return list;
	}
	
	public void createTestReception(TestReception testreception) {
		testreceptionsDao.testReceptionInsert(testreception);
	}
	
	public int resultstatus(int testreceptionid) {

		testreceptionsDao.resultstatus(testreceptionid);
		return testreceptionsDao.resultstatus(testreceptionid);
	
	}
	
	public int startpatient(int testreceptionid) {

		testreceptionsDao.startpatient(testreceptionid);
		return testreceptionsDao.startpatient(testreceptionid);
	
	}
	
	public int cancelpatient(int testreceptionid) {

		testreceptionsDao.startpatient(testreceptionid);
		return testreceptionsDao.cancelpatient(testreceptionid);
	
	}
	
	public int finishpatient(int testreceptionid) {

		testreceptionsDao.startpatient(testreceptionid);
		return testreceptionsDao.finishpatient(testreceptionid);
	
	}
	
	
}
