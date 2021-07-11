package com.mycompany.webapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Test;

@Mapper
public interface TestsDao {
	public int insertList(Map<String, Object> testData);
	public List<Test> selectbyTestReceptionid(@Param("testreceptionid") int testreceptionid);
	public List<Test> selectByTreatmentId(int treatmentid);
	public int changeWaitingStatus(List<Test> test);
//	public List<Test> changeWaitingStatus(Test test);
}
