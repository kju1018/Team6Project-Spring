package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Test;

@Mapper
public interface TestsDao {
	public int insert(Test test);
	public List<Test> selectbyTestReceptionid(int testreceptionid);
}
