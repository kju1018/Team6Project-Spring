package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Diagnoses;

@Mapper
public interface DiagnosesDao {
	public List<Diagnoses> selectAll();
	public List<Diagnoses> selectByTreatmentId(int treatmentid);
	public int insertList(List<Diagnoses> diagnosesList);

}
