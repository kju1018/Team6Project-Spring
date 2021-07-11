package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestImgsDao;
import com.mycompany.webapp.dto.TestImg;

@Service
public class TestImgsService {
	@Autowired TestImgsDao testimgsDao;
	
	public TestImg getTestimg(int treatmentid) {
		return testimgsDao.selectByTreatmentid(treatmentid);
	}

	public int insertImg(TestImg testimg) {
		return testimgsDao.insertImg(testimg);
	}
	
}
