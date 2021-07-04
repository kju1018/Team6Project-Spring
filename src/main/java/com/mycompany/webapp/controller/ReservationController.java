package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.service.ReservationsService;

@RestController
@RequestMapping("/Reservation")

public class ReservationController {

	@Autowired
	ReservationsService reservationservice;
	
	@GetMapping("/list")
	public List<Reservation> ReservationList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Reservation> list= reservationservice.getReservationDatas();
		
		System.out.println(list.get(0).getReservationdate());
		map.put("list", list);
		return list;
	}
}
