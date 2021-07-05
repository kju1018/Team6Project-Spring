package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.Reservation;


	@Mapper
	public interface PatientsDao {
		//환자등록
		public int insert(Patient patient);
		//환자수정
		public int update(Patient patient);
		//전체환자 가져오기
		public List<Patient> selectAll();
		//특정환자 가져오기
		public Patient selectByPatient(String patientid);
	}


