package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Schedule;

@Mapper
public interface SchedulesDao {
	//스케쥴 가져오기
	public List<Schedule> selectAll();
	
}
