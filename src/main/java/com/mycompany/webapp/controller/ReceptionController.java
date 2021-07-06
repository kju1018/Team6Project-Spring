package com.mycompany.webapp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.dto.Diagnoses;
import com.mycompany.webapp.dto.Drug;
import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.service.DiagnosesService;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.PatientsService;
import com.mycompany.webapp.service.ReceptionsService;
import com.mycompany.webapp.service.ReservationsService;

@RestController
@RequestMapping("/reception")

public class ReceptionController {

	@Autowired
	ReservationsService reservationservice;
	@Autowired
	PatientsService patientservice; 
	@Autowired
	DrugsService drugsService;
	@Autowired
	DiagnosesService diagnosesService;
	@Autowired
	ReceptionsService receptionsService;
	
	//전체 예약정보 가져오기
	@GetMapping("/reservationlist")
	public List<Reservation> ReservationList() {
		List<Reservation> list= reservationservice.getReservationList();	
		return list;
	}
	//예약정보 등록하기
	@PostMapping("/registerreservation")
	public Reservation RegisterReservation(@RequestBody List<Object> obj) {
		
		//추가된 예약 데이터
		ObjectMapper mapper = new ObjectMapper();
		Reservation newreservation=null;
		List<Test> testlist = null;
		try {
			newreservation = mapper.readValue(mapper.writeValueAsString(obj.get(0)), Reservation.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//진료 예약일때
		if(obj.get(1)==null) {
			Date date = newreservation.getReservationdate();
			newreservation.setReservationdate(date);
			reservationservice.RegisterReservation(newreservation);
		}
		// 검사예약일때
		else {
			//예약된 검사데이터리스트
			List<Test> newtestlist;	
		}
		
		return newreservation;
	}
	//예약정보 삭제하기
	@DeleteMapping("/removereservation")
	public int RemoveReservation(@RequestBody Map<String,Integer> obj) {
		return reservationservice.RemoveReservation(obj.get("reservationid"));
	}
	//예약정보 수정하기
	@PutMapping("/updatereservation")
	public int UpdateReservation(@RequestBody Reservation reservation) {
		reservationservice.UpdateReservation(reservation);
		return reservation.getReservationid();
	}
	
	
	//전체 환자정보 가져오기
	@GetMapping("/patientlist")
	public List<Patient> PatientList() {
		List<Patient> list= patientservice.getPatientList();	
		return list;
	}
	//환자등록하기
	@PostMapping("/registerpatient")
	public int RegisterPatient(@RequestBody Patient patient) {
		patientservice.RegisterPatient(patient);
		return patient.getPatientid();
	}
	//환자수정하기
	@PutMapping("/updatepatient")
	public int UpdatePatient(@RequestBody Patient patient) {
		patientservice.UpdatePatient(patient);
		return patient.getPatientid();
	}
	
	//해당 진료의 상세정보 불러오기
	@GetMapping("/treatmentdetail")
	public Map<String,Object> TreatmentDetail(int treatmentid) {
		List<Drug> drugList = drugsService.getDrugsByTreatmentId(treatmentid);	
		List<Diagnoses> diagnosesList = diagnosesService.getDiagnosesByTreatmentId(treatmentid);
		
		List<String> PrescriptionTestDataIdList = receptionsService.GetPrescriptionTestDataByTreatmentid(treatmentid);
		List<TestData> PrescriptionTestDataList = new ArrayList<TestData>();
		for(int i=0; i<PrescriptionTestDataIdList.size(); i++) {
			TestData testdata = receptionsService.GetTestData(PrescriptionTestDataIdList.get(i));
			PrescriptionTestDataList.add(testdata);
		}
		
		
		List<Object> list = new ArrayList<Object>();
		list.add(diagnosesList);
		list.add(drugList);
		list.add(PrescriptionTestDataList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("treatmentdetail", list);
		return map;
	}
	//테스트
	@GetMapping("/test")
	public List<Object> test(int treatmentid, int patientid) {
		List<String> tlist = receptionsService.GetPrescriptionTestDataByTreatmentid(treatmentid);
		List<String> plist = receptionsService.GetPrescriptionTestDataByPatientid(patientid);
		List<Object> list = new ArrayList<Object>();
		list.add(tlist);
		list.add(plist);
		return list;
	}
	
	
	
}
