package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.TestReception;

@Mapper
public interface TestReceptionsDao {
	public int testReceptionInsert(TestReception testreception);

	public List<TestReception> selectbyTestdate(@Param("startdate") String startdate, @Param("enddate") String enddate);
	public List<TestReception> selectbyPatientid(@Param("patientid") int patientid);
	
	public int resultstatus(@Param("testreceptionid") int testreceptionid);
	public int startpatient(@Param("testreceptionid") int testreceptionid);
	public int cancelpatient(@Param("testreceptionid") int testreceptionid);
	public int finishpatient(@Param("testreceptionid") int testreceptionid);
}
