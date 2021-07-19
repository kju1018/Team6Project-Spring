package com.mycompany.webapp.service;

import java.util.List;

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
	
	public List<Schedule> getScheduleList() {
		List<Schedule> list = schedulesDao.selectAll();
		return list;
	}
}
