package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ReservationsDao;
import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Reservation;


@Service
public class ReservationsService {
	@Autowired
	private ReservationsDao reservationsDao;
	
	//예약 정보등록
	public int RegisterReservation(Reservation reservation) {
		return reservationsDao.insert(reservation);
	}
	//전체 예약정보 가져오기
	public List<Reservation> getReservationList() {
		return reservationsDao.selectAll();
	}
	//예약정보 삭제
	public int RemoveReservation(int reservationid) {
		return reservationsDao.deleteByReservationid(reservationid);
	}
}
