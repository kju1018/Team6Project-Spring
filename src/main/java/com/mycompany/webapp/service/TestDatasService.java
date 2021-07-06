package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestDatasDao;
import com.mycompany.webapp.dto.TestData;

@Service
public class TestDatasService {
	private static final Logger logger = 
			LoggerFactory.getLogger(TestsService.class);
	@Autowired
	private TestDatasDao testdatasDao;
	
	public List<TestData> getTestDataList() {
		List<TestData> list = testdatasDao.selectAll();
		return list;
	}
	
	public void createTest(TestData testdatas) {
		testdatasDao.insert(testdatas);
	}
	
}
