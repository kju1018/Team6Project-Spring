package com.mycompany.webapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.service.TestDatasService;
import com.mycompany.webapp.service.TestReceptionsService;
import com.mycompany.webapp.service.TestsService;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestsService testsService;
	
	@Autowired
	private TestDatasService testdatasService;
	
	@Autowired
	private TestReceptionsService testreceptionsService;
	
	
	@GetMapping("/patientbytestdate")
	public List<TestReception> getTestReceptionByTestdate (@RequestParam String startdate, @RequestParam String enddate) {
		System.out.println("dddd" + startdate);
		
		logger.info(startdate);
		logger.info(enddate);
		List<TestReception> list = testreceptionsService.getTestReceptionListByDate(startdate, enddate);
		
		System.out.println("dddd" + list);
		return list;
	}
	
	@GetMapping("/testreceptionbypatientid/{patientid}")
	public List<TestReception> getTestReceptionListByPatientId (@PathVariable int patientid) {
		List<TestReception> list = testreceptionsService.getTestReceptionListByPatientId(patientid);
		logger.info("patientid");
		return list;
	}
	
	@GetMapping("/testlistbyreceptionid/{testreceptionid}")
	public List<Test> getTestReceptionListByReceptionId(@PathVariable int testreceptionid) {
		logger.info("testreceptionid");
		List<Test> testreceptionlistbyid = testsService.selectbyTestReceptionid(testreceptionid);
		
		return testreceptionlistbyid;
	}
	
	
}