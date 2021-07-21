package com.mycompany.webapp.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Schedule;

@Mapper
public interface SchedulesDao {
	//스케쥴 가져오기
	public List<Schedule> selectAll(String startDate);
	//스케쥴 삽입
	public int insert(Schedule schedule);
	//스케쥴 삭제
	public int deleteSchedule(int scheduleid);
}
