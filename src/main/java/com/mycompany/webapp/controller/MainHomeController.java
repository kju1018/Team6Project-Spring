package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Notice;
import com.mycompany.webapp.dto.Schedule;
import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.service.NoticesService;
import com.mycompany.webapp.service.SchedulesService;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/main")
public class MainHomeController {

	@Autowired
	private NoticesService noticesService;
	@Autowired
	private SchedulesService schedulesService;
	@Autowired
	private UsersService usersService;

	//전체공지 불러오기
	@GetMapping("/NoticeList")
	//public 넘겨줄데이터타입 함수이름(받을데이터)
	public List<Notice> NoticeList(){
		List<Notice> list = noticesService.getNoticeList();
		return list;
	}
	
	//'공지사항 등록'버튼 클릭
	@PostMapping("/noticeUpdate")
	public Map<String, String> noticeUpdate(@RequestBody Notice notice) {
		System.out.println(notice.toString());
		return noticesService.noticeUpdate(notice);
	}
	//'공지사항 삭제'버튼 클릭
	@DeleteMapping("/delete/{noticeid}")
	public List<Notice> deleteNotice(@PathVariable int noticeid) {
		noticesService.deleteNotice(noticeid);
		List<Notice> list = noticesService.getNoticeList();
		return list;
	}
	
	//'유저리스트' 불러오기
	@GetMapping ("/userList")
	public List<User> userList(){
		List<User> list = usersService.getUserList("의사");
		return list;
	}
	
	//'스케줄' 불러오기
	@GetMapping("/ScheduleList/{startDate}")
	public List<Schedule> ScheduleList(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") String startDate){
		//클래스명 이름 = new 클래스명(); - 기본 자바 상식
		
		//moment 간단한방법
		System.out.println(startDate);
		
		List<Schedule> list = schedulesService.getScheduleList(startDate);
		return list;
	}
	
	//'스케줄 등록' 버튼 클릭
	@PostMapping("/scheduleUpdate")
	public Map<String, String> scheduleUpdate(@RequestBody Schedule schedule) {
		return schedulesService.scheduleUpdate(schedule);
	}
	
	//'스케줄 삭제' 버튼 클릭
	@GetMapping("/scheduleDelete/{scheduleid}/{startDate}")
	public List<Schedule> deleteSchedule(@PathVariable String scheduleid, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") String startDate) {
		System.out.println(scheduleid);
		System.out.println(startDate);
		int value = Integer.parseInt(scheduleid);
		schedulesService.deleteSchedule(value);
		List<Schedule> list = schedulesService.getScheduleList(startDate);
		return list;
	}
	
}
