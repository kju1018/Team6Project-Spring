package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Reservation;


	@Mapper
	public interface ReservationsDao {
		public int insert(Reservation reservation);
		public int deleteByReservationid(int reservationid);
		public int update(Reservation reservation);
		public List<Reservation> selectAll();
	}


