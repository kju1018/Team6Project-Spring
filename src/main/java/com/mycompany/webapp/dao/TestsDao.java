package com.mycompany.webapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestImg;
import com.mycompany.webapp.dto.TestReception;

@Mapper
public interface TestsDao {
	public int insertList(Map<String, Object> testData);
	public List<Test> selectbyTestReceptionid(@Param("testreceptionid") int testreceptionid);
	public List<Test> selectByTreatmentId(int treatmentid);
	
	public int result(Test test);
	
	public int insertresult(TestImg testimg);
	
	public int changeWaitingStatus(List<Test> test);
	public int changeCancelStatus(List<Test> test);
	public int changeFinishStatus(List<Test> test);
	
}
