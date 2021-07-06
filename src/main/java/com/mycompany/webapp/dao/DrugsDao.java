package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Drug;

@Mapper
public interface DrugsDao {
	public List<Drug> selectAll();
	public List<Drug> selectByTreatmentId(int treatmentid);
	public int insertList(List<Drug> drugList);
}
