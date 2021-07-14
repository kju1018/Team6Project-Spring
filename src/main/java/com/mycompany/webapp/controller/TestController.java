package com.mycompany.webapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestImg;
import com.mycompany.webapp.service.TestDatasService;
import com.mycompany.webapp.service.TestImgsService;
import com.mycompany.webapp.service.TestReceptionsService;
import com.mycompany.webapp.service.TestsService;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestsService testsService;
	
	@Autowired
	private TestImgsService testimgsService;
	
	@Autowired
	private TestDatasService testdatasService;
	
	@Autowired
	private TestReceptionsService testreceptionsService;
	
	
	@GetMapping("/patientbytestdate")
	public List<TestReception> getTestReceptionByTestdate (@RequestParam String startdate, @RequestParam String enddate) {
		System.out.println(startdate);
		System.out.println(enddate);
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
	
	@PutMapping("/result") 
	public int Result (@RequestBody Test test){
		testsService.result(test);
		return testsService.result(test);
	}
	
	@PutMapping("/starttest")
	public int StartTest(@RequestBody ArrayList<List<Test>> checkedList) {
		String success = "성공";
		//System.out.println("DDDD"+checkedList.size());
		
		List<Test> test = new ArrayList<Test>();
		for(int i=0; i<checkedList.size(); i++) {
			List<Test> tests = checkedList.get(i);
			for(int j=0; j<tests.size(); j++) {
				tests.get(j);
				//ystem.out.println(tests.get(j));
				test.add(tests.get(j));
			}
		}
		
		//System.out.println(test);

		testsService.starttest(test);
		return testsService.starttest(test);
	}
	
	@PutMapping("/teststartpatient/{testreceptionid}") 
	public int StartsPatient (@PathVariable int testreceptionid){
		String success = "성공";
		//System.out.println(testreceptionid);
		testreceptionsService.startpatient(testreceptionid);
		return testreceptionsService.startpatient(testreceptionid);
	}
	
	@PutMapping("/canceltest")
	public int CancelTest(@RequestBody ArrayList<List<Test>> checkedList) {
		String success = "성공";
		//System.out.println("DDDD"+checkedList.size());
		
		List<Test> test = new ArrayList<Test>();
		for(int i=0; i<checkedList.size(); i++) {
			List<Test> tests = checkedList.get(i);
			for(int j=0; j<tests.size(); j++) {
				tests.get(j);
				System.out.println(tests.get(j));
				test.add(tests.get(j));
			}
		}
		
		//System.out.println(test);

		testsService.canceltest(test);
		return testsService.canceltest(test);
	}
	
	@PutMapping("/testcancelpatient/{testreceptionid}") 
	public int CancelPatient (@PathVariable int testreceptionid){
		System.out.println(testreceptionid);
		testreceptionsService.cancelpatient(testreceptionid);
		return testreceptionsService.cancelpatient(testreceptionid);
	}
	
	@PutMapping("/finishtest")
	public int FinishTest(@RequestBody ArrayList<List<Test>> checkedList) {
		//System.out.println("DDDD"+checkedList.size());
		
		List<Test> test = new ArrayList<Test>();
		for(int i=0; i<checkedList.size(); i++) {
			List<Test> tests = checkedList.get(i);
			for(int j=0; j<tests.size(); j++) {
				tests.get(j);
				//System.out.println(tests.get(j));
				test.add(tests.get(j));
			}
		}
		
		//System.out.println(test);

		testsService.finishtest(test);
		return testsService.finishtest(test);
	}
	
	@PutMapping("/testfinishpatient/{testreceptionid}") 
	public int FinishPatient (@PathVariable int testreceptionid){
		System.out.println("dd");
		testreceptionsService.finishpatient(testreceptionid);
		return testreceptionsService.finishpatient(testreceptionid);
	}
	
	@PostMapping("/xray")
	public TestImg create(TestImg testimg) {
		System.out.println(testimg.getTreatmentid());
		String treatmentid = testimg.getTestdataid();
		logger.info(testimg.getTestdataid());
		if(testimg.getBattach() != null && !testimg.getBattach().isEmpty()) {
			MultipartFile mf = testimg.getBattach();
			System.out.println(mf);
			testimg.setOname(mf.getOriginalFilename());
			testimg.setSname(treatmentid + "_"+ new Date() + "_" + mf.getOriginalFilename());
			testimg.setItype(mf.getContentType());
			try {
				File file = new File("C:/MyProjects/uploadfiles" + testimg.getSname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		testimgsService.insertImg(testimg);
		testsService.insert(testimg);
		testimg.setBattach(null); //JSON으로 표현불가하므로 null 처리

		return testimg;
	}
	
	@GetMapping("/battach/{treatmentid}")
	public void download(@PathVariable int treatmentid, HttpServletResponse response) {
		try {
			TestImg testimg = testimgsService.getTestimg(treatmentid);
			String oname = testimg.getOname();
			if(oname == null) return;
			oname = new String(oname.getBytes("UTF-8"),"ISO-8859-1");
			String sname = testimg.getSname();		
			String battachspath = "C:/MyProjects/uploadfiles/" + sname;
			String itype = testimg.getItype();
	
			response.setHeader("Content-Disposition", "attachment; filename=\""+oname+"\";");
			response.setContentType(itype);

			InputStream is = new FileInputStream(battachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}