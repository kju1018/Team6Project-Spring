package com.mycompany.webapp.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.dto.Diagnoses;
import com.mycompany.webapp.dto.Drug;
import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.dto.TestImg;
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.service.DiagnosesService;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.PatientsService;
import com.mycompany.webapp.service.TestDatasService;
import com.mycompany.webapp.service.TestImgsService;
import com.mycompany.webapp.service.TestsService;
import com.mycompany.webapp.service.TreatmentsService;

@RestController
@RequestMapping("/treatment")
public class TreatmentsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TreatmentsController.class);
	
	@Autowired
	private DrugsService drugsService;
	
	@Autowired
	private TreatmentsService treatmentsService;
	
	@Autowired
	private DiagnosesService diagnosesService;
	
	@Autowired
	private PatientsService patientsService;
	
	@Autowired
	private TestDatasService testDatasService;
	
	@Autowired
	private TestsService testsService;
	
	@Autowired
	private TestImgsService testImgsService;
	
	//????????? ?????? ????????? ????????????
	@GetMapping("/treatments/{patientid}")
	public List<Treatment> getTreatmentList(@PathVariable String patientid) {
		
		if(patientid.equals("undefined")) {
			System.out.println("null");
			return null;
		} else {
			return treatmentsService.getTreatments(patientid);
			
		}	
	}
	
	//???????????? ?????? ????????????
	@GetMapping("/getprescription/{treatmentid}")
	public Map<String, List> getPrescriptionList(@PathVariable int treatmentid) {
		Map<String, List> map = new HashMap<String, List>();
		List<Drug> drugList = drugsService.getDrugsByTreatmentId(treatmentid);
		List<Diagnoses> diagnosesList = diagnosesService.getDiagnosesByTreatmentId(treatmentid);
		List<Test> testsList = testsService.getTestsByTreatmentId(treatmentid);
		map.put("drugsList", drugList);
		map.put("diagnosesList", diagnosesList);
		map.put("testsList", testsList);
		return map;
	}
	
	//???????????? ????????? ?????? ????????????(???????????? ??????)
	@GetMapping("/gettestlist/{treatmentid}")
	public List<Test> getTestList(@PathVariable int treatmentid) {
		List<Test> testList = testsService.getTestsByTreatmentId(treatmentid);
		return testList;
	}
	
	
	
	/*
	 * HashMap??? prescription
	 * 
	 * treatmentDrugs: ???????????? ??? ?????????
	 * treatmentDiagnoses: ???????????? ?????? ?????????
	 * treatmentTests: ???????????? ?????? ?????????
	 * treatment: ?????? ??????
	 * userid: ????????? ??? ?????? ?????????
	 * patientid: ?????? ?????? ?????????
	 * */
	//???????????? ?????? ??????
	@PostMapping("/prescribetreatment")
	public String prescribeTreatment(@RequestBody HashMap prescription) {
		//map?????? ?????? ?????? ????????????
		ObjectMapper mapper = new ObjectMapper();
		List<Drug> drugList = mapper.convertValue(prescription.get("treatmentDrugs"), new TypeReference<List<Drug>>() { });
		List<Diagnoses> diagnosesList = mapper.convertValue(prescription.get("treatmentDiagnoses"), new TypeReference<List<Diagnoses>>() { });
		List<Test> testList = mapper.convertValue(prescription.get("treatmentTests"), new TypeReference<List<Test>>() { });
		Treatment nowTreatment = mapper.convertValue(prescription.get("treatment"), Treatment.class);
		String userid = (String) prescription.get("userid");
		int patientid = (int) prescription.get("patientid");
		logger.info(nowTreatment.toString());
		//test???????????? ?????? ????????? ??????
		Map<String, Object> testData = new HashMap<String, Object>();
		testData.put("testList", testList);
		testData.put("userid", userid);
		testData.put("patientid", patientid);
		testData.put("treatmentid", nowTreatment.getTreatmentid());

		testsService.insertTestList(testData);
		
		drugsService.insertDrugList(drugList);
		diagnosesService.insertDiagnosesList(diagnosesList);
		
		patientsService.UpdateLastTreatment(patientid);

		nowTreatment.setStatus("?????? ??????");
		treatmentsService.updateStatus(nowTreatment);
		return "success";
	}
	
	//????????? ????????? ??????
	@PutMapping("/updatetreatment")
	public String updateTreatment(@RequestBody Treatment treatment) {
		treatment.setStatus("?????????");
		treatmentsService.updateStatus(treatment);
		
		return "success";
	}
	
	//?????? ????????? ??????-----------------------
	@GetMapping("/staticdrugs")
	public List<Drug> getStaticDrugList() {
		List<Drug> staticDrugList = drugsService.getStaticDrugs();
		return staticDrugList;
	}
	
	@GetMapping("/staticdiagnoses")
	public List<Diagnoses> getStaticDiagnosesList() {
		List<Diagnoses> staticDiagnosesList = diagnosesService.getStaticDiagnoses();
		return staticDiagnosesList;
	}
	
	@GetMapping("/statictests")
	public List<TestData> getStaticTestList() {
		List<TestData> staticTests = testDatasService.getTestDataList();
		return staticTests;
	}
	//------------------------------------
	
	//?????? ????????????-----------------------
	@GetMapping("/getpatientlist")
	public List<Patient> getPatientList() {
		List<Patient> patientList = patientsService.getPatientList();
		return patientList;
	}
	
	//?????? ?????? ????????????------
	@GetMapping("/getpatient/{patientid}")
	public Patient getPatient(@PathVariable int patientid) {
		Patient patient = patientsService.getPatient(patientid);
		return patient;
	}
	
	//????????? ????????? ????????????
	@GetMapping("/getimgList")
	public List<TestImg> getImgList(int treatmentid, String testdataid) {
		Map<String, Object> map = new HashMap();
		map.put("treatmentid", treatmentid);
		map.put("testdataid", testdataid);
		List<TestImg> imgList = testImgsService.getImgList(map);
		return imgList;
	}
	
	// ????????? ????????????
	@GetMapping("/imgdownload/{imgid}")
	public void download(@PathVariable int imgid, HttpServletResponse response) {
		try {
			TestImg testimg = testImgsService.getImgById(imgid);
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
