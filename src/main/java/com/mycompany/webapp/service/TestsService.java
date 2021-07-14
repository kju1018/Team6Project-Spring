package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestsDao;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestImg;
import com.mycompany.webapp.dto.TestReception;


@Service
public class TestsService { 

	private static final Logger logger = 
			LoggerFactory.getLogger(TestsService.class);
	@Autowired
	private TestsDao testsDao;
	
	public List<Test> selectbyTestReceptionid(int testreceptionid) { 
		List<Test> list = testsDao.selectbyTestReceptionid(testreceptionid);
		return list;
	}
	
	public int insertTestList(Map<String, Object> testData) { 
		int row = 0;
		if(((List<Test>) testData.get("testList")).size() > 0 ) {
			row = testsDao.insertList(testData);
		}
		return row;
	}
	
	public int insert(TestImg testimg) { 
		int row = testsDao.insertresult(testimg);
		return row;
	}
	
	public List<Test> getTestsByTreatmentId(int treatmentid){
		return testsDao.selectByTreatmentId(treatmentid);
	}
	
	public int result(Test test) {

		testsDao.result(test);
	return testsDao.result(test);
}
	
	public int starttest(List<Test> test) {

			testsDao.changeWaitingStatus(test);
		return testsDao.changeWaitingStatus(test);
	}
	

	public int canceltest(List<Test> test) {

		testsDao.changeCancelStatus(test);

	return testsDao.changeCancelStatus(test);
	}
	
	public int finishtest(List<Test> test) {

		testsDao.changeFinishStatus(test);

	return testsDao.changeFinishStatus(test);
	}
}
