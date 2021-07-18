package com.mycompany.webapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.NoticesDao;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Notice;
import com.mycompany.webapp.dto.User;


@Service 
public class NoticesService {
	@Autowired
	private NoticesDao noticesDao;
	private static final Logger logger = LoggerFactory.getLogger(NoticesService.class);
	@Autowired
	private UsersDao usersDao;
	
	public List<Notice> getNoticeList() {
		List<Notice> list = noticesDao.selectAll();
		return list;
	}
	public Map<String, String> noticeUpdate(Notice notice) {
		Map<String, String> map = new HashMap<String, String>();
		noticesDao.insert(notice);
		return map;
	}
	
	public int deleteNotice(int noticeid) {
		return noticesDao.deleteNotice(noticeid);
	}
}

