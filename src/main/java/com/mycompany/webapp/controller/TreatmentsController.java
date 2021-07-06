package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.service.DiagnosesService;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.PatientsService;
import com.mycompany.webapp.service.TestDatasService;
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
	
	@DeleteMapping("/test")
	public String test() {
		Treatment treatment = new Treatment();
		int row = treatmentsService.deleteTreatment(997);
		System.out.println(row);
		return "test";
	}
	
	@GetMapping("/treatments/{patientid}")
	public List<Treatment> getTreatmentList(@PathVariable String patientid) {
		
		if(patientid.equals("undefined")) {
			System.out.println("null");
			return null;
		} else {
			return treatmentsService.getTreatments(patientid);
			
		}	
	}
	//처방받은 내역 불러오기
	@GetMapping("/{treatmentid}")
	public Map<String, List> getPrescriptionList(@PathVariable int treatmentid) {
		Map<String, List> map = new HashMap<String, List>();
		List<Drug> drugList = drugsService.getDrugsByTreatmentId(treatmentid);
		List<Diagnoses> diagnosesList = diagnosesService.getDiagnosesByTreatmentId(treatmentid);
		map.put("drugsList", drugList);
		map.put("diagnosesList", diagnosesList);
		return map;
	}
	
	//처방받은 내역 저장
	@PostMapping("/prescribetreatment")
	public String prescribereatment(@RequestBody HashMap prescription) {
		ObjectMapper mapper = new ObjectMapper();
		List<Drug> drugList = mapper.convertValue(prescription.get("treatmentDrugs"), new TypeReference<List<Drug>>() { });
		List<Diagnoses> diagnosesList = mapper.convertValue(prescription.get("treatmentDiagnoses"), new TypeReference<List<Diagnoses>>() { });
//		List<Test> testList = mapper.convertValue(prescription.get("treatmentTests"), new TypeReference<List<Test>>() { });
		Treatment nowTreatment = mapper.convertValue(prescription.get("treatment"), Treatment.class);
		
		logger.info(nowTreatment.toString());
//		drugsService.insertDrugList(drugList);
//		diagnosesService.insertDiagnosesList(diagnosesList);
//		testsService.insertTestList(testList);
		 
		/*		nowTreatment.setStatus("진료 완료");
				treatmentsService.updateStatus(nowTreatment);*/
		return "success";
	}
	
	//정적 데이터 관련-----------------------
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
//		List<TestData> staticTests = testDatasService.getTestDataList();
//		logger.info(staticTests.toString());
		return null;
	}
	//------------------------------------
	
	//환자 불러오기-----------------------
	@GetMapping("/getpatientList")
	public List<Patient> getPatientList() {
		List<Patient> patientList = patientsService.getPatientList();
		return patientList;
	}
	
	
	
}
