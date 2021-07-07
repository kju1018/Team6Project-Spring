package com.mycompany.webapp.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestsDao;
import com.mycompany.webapp.dto.Test;


@Service
public class TestsService { 

	private static final Logger logger = 
			LoggerFactory.getLogger(TestsService.class);
	@Autowired
	private TestsDao testsDao;
	
	public List<Test> selectbyTestReceptionid(int testreceptionid) { //접수에서 환자 검사 접수 시 필요
		List<Test> list = testsDao.selectbyTestReceptionid(testreceptionid);
		return list;
	}
	
	public int insertTestList(Map<String, Object> testData) { //진료 페이지에서 검사 추가
		int row = 0;
		if(((List<Test>) testData.get("testList")).size() > 0 ) {
			row = testsDao.insertList(testData);
		}
		return row;
	}
	
	public List<Test> getTestsByTreatmentId(int treatmentid){
		return testsDao.selectByTreatmentId(treatmentid);
	}
}
