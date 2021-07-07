package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Treatment;

@Mapper
public interface TreatmentsDao {
	
	public List<Treatment> selectByPatientId(String patientid);
	public int insert(Treatment treatment);
	public int delete(int treatmentid);
	public int update(Treatment treatment);
}
