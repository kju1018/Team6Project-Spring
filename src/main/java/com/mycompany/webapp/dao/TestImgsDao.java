package com.mycompany.webapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.TestImg;

@Mapper
public interface TestImgsDao {
	public TestImg selectByTreatmentid(int treatmentid);
	public int insertImg(TestImg testimg);
}
