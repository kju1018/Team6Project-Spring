package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.TestData;


@Mapper 
public interface TestDatasDao {
	public String insert(TestData testdata);
	
	public List<TestData> selectAll();
}
