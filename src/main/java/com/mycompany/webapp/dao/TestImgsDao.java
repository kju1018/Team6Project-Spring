package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.TestImg;

@Mapper
public interface TestImgsDao {
	public TestImg selectByTreatmentid(int treatmentid);
	public int insertImg(TestImg testimg);
	public List<TestImg> selectById(Map<String, Object> testinfo);
}
