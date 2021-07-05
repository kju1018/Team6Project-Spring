package com.mycompany.webapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Diagnoses;
import com.mycompany.webapp.dto.Drug;
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.service.DiagnosesService;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.TreatmentsService;

@RestController
@RequestMapping("/treatment")
public class TreatmentsController {
	
	@Autowired
	private DrugsService drugsService;
	
	@Autowired
	private TreatmentsService treatmentsService;
	
	@Autowired
	private DiagnosesService diagnosesService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/treatments/{patientid}")
	public List<Treatment> getTreatment(@PathVariable String patientid) {
		
		if(patientid.equals("undefined")) {
			System.out.println("null");
			return null;
		} else {
			System.out.println(treatmentsService.getTreatments(patientid).toString());
			return treatmentsService.getTreatments(patientid);
			
		}	
	}

	//정적 데이터 관련
	@GetMapping("/staticdrugs")
	public List<Drug> getStaticDrugs() {
		List<Drug> staticDrugList = drugsService.getStaticDrugs();
		return staticDrugList;
	}
	
	//정적 데이터 관련
	@GetMapping("/staticdiagnoses")
	public List<Diagnoses> getStaticDiagnoses() {
		List<Diagnoses> staticDiagnosesList = diagnosesService.getStaticDiagnoses();
		return staticDiagnosesList;
	}
}
