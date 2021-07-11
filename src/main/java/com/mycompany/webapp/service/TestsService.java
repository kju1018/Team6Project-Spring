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


@Service
public class TestsService { 

	private static final Logger logger = 
			LoggerFactory.getLogger(TestsService.class);
	@Autowired
	private TestsDao testsDao;
	
	public List<Test> selectbyTestReceptionid(int testreceptionid) { //�젒�닔�뿉�꽌 �솚�옄 寃��궗 �젒�닔 �떆 �븘�슂
		List<Test> list = testsDao.selectbyTestReceptionid(testreceptionid);
		return list;
	}
	
	public int insertTestList(Map<String, Object> testData) { //吏꾨즺 �럹�씠吏��뿉�꽌 寃��궗 異붽�
		int row = 0;
		if(((List<Test>) testData.get("testList")).size() > 0 ) {
			row = testsDao.insertList(testData);
		}
		return row;
	}
	
	public List<Test> getTestsByTreatmentId(int treatmentid){
		return testsDao.selectByTreatmentId(treatmentid);
	}
	
	public int starttest(List<Test> test) {

			testsDao.changeWaitingStatus(test);

		return testsDao.changeWaitingStatus(test);
	}
}
