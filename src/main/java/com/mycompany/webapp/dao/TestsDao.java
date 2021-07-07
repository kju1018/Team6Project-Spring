package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Test;

@Mapper
public interface TestsDao {
	public int insertList(Map<String, Object> testList);
	public List<Test> selectbyTestReceptionid(@Param("testreceptionid") int testreceptionid);

}
