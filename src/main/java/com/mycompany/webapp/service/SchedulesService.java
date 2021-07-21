package com.mycompany.webapp.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.SchedulesDao;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Schedule;

@Service
public class SchedulesService {
	@Autowired
	private SchedulesDao schedulesDao;
	@Autowired
	private UsersDao usersDao;

	public List<Schedule> getScheduleList(String startDate) {
		List<Schedule> list = schedulesDao.selectAll(startDate);
		return list;
	}
	
	public Map<String, String> scheduleUpdate(Schedule Schedule){
		Map<String, String> map = new HashMap<String, String>();
		schedulesDao.insert(Schedule);
		return map;
	}
	
	
	public int deleteSchedule(int scheduleid) {
		return schedulesDao.deleteSchedule(scheduleid); 
	}
	

}
