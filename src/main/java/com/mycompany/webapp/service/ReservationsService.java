package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ReservationsDao;
import com.mycompany.webapp.dto.Reservation;


@Service
public class ReservationsService {
	@Autowired
	private ReservationsDao reservationsDao;

	public List<Reservation> getReservationDatas() {
		return reservationsDao.selectAll();
	}
}
