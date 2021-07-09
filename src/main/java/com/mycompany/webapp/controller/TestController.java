package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<TestReception> list = testreceptionsService.getTestReceptionListByDate(startdate, enddate);
		
		return list;
	} 
	
	@GetMapping("/testreceptionbypatientid")
	public List<TestReception> getTestReceptionListByPatientId (@RequestParam int patientid) {
		List<TestReception> list = testreceptionsService.getTestReceptionListByPatientId(patientid);

		return list;
	}  
	
	@GetMapping("/testlistbyreceptionid")
	public List<Test> getTestReceptionListByReceptionId(@RequestParam int testreceptionid) {
		List<Test> testreceptionlistbyid = testsService.selectbyTestReceptionid(testreceptionid);

		return testreceptionlistbyid;
	}   
	
	@PutMapping("/starttest")
	public ArrayList<Test> StartTest(@RequestBody ArrayList<List<Test>> checkedList) {
		System.out.println("DDDD"+checkedList.size());
		
		List test = new ArrayList();
		for(int i=0; i<checkedList.size(); i++) {
			List<Test> tests = checkedList.get(i);
			for(int j=0; j<tests.size(); j++) {
				tests.get(j);
				System.out.println(tests.get(j));
				test.add(tests.get(j));
			}
		}
		
		System.out.println(test);
		//List<Object> list = checkedList.get(0);
		
		//List<Test> starttest = testsService.starttest(list);
		return null;
	}
	
	@PostMapping("/upload")
	public void insertImg() {
	
	}
	
	
}