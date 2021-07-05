package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.service.PatientsService;
import com.mycompany.webapp.service.ReservationsService;

@RestController
@RequestMapping("/reception")

public class ReceptionController {

	@Autowired
	ReservationsService reservationservice;
	@Autowired
	PatientsService patientservice; 
	
	//전체 예약정보 가져오기
	@GetMapping("/reservationlist")
	public List<Reservation> ReservationList() {
		List<Reservation> list= reservationservice.getReservationList();	
		return list;
	}
	//예약정보 등록하기
	@PostMapping("/registerreservation")
	public int RegisterReservation(@RequestBody Reservation reservation) {
		reservationservice.RegisterReservation(reservation);
		return reservation.getReservationid();
	}
	//전체 환자정보 가져오기
	@GetMapping("/patientlist")
	public List<Patient> PatientList() {
		List<Patient> list= patientservice.getPatientList();	
		System.out.print(list.toString());
		return list;
	}
	//환자등록하기
	@PostMapping("/registerpatient")
	public int RegisterPatient(@RequestBody Patient patient) {
		patientservice.RegisterPatient(patient);
		return patient.getPatientid();
	}
	//환자수정하기
	@PostMapping("/updatepatient")
	public int UpdatePatient(@RequestBody Patient patient) {
		patientservice.UpdatePatient(patient);
		return patient.getPatientid();
	}
	
	
}
