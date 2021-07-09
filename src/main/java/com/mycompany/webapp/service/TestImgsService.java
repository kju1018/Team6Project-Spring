package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.TestImgsDao;
import com.mycompany.webapp.dto.TestImg;

@Service
public class TestImgsService {
	@Autowired TestImgsDao testimgsDao;
	
	public void insertImg(TestImg testimg) {
		testimgsDao.insertImg(testimg);
	}
	
}
