package com.mycompany.webapp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsersService usersService;
	
	//유저정보 가져오기 usertype => 의사 / 간호사/ 전체
	@GetMapping("/userlist")
	public List<User> userList(String usertype) {
		
		List<User> list= usersService.getUserList(usertype);
		return list;
	}
	
	//직원 등록
	@PostMapping("/join")
	public Map<String, String> join(@RequestBody User user) {
		return usersService.join(user);
		 
	}
	
	//직원 수정
	@PostMapping("/update")
	public Map<String, String> update(@RequestBody User user) {
		return usersService.updateUser(user);
	}
	
	//직원 삭제
	@DeleteMapping("/delete/{userid}")
	public Map<String, String> delete(@PathVariable String userid) {
		return usersService.deletUser(userid);
	}
	
	//직원 비활성화
	@PutMapping("/disable/{userid}")
	public Map<String, String> disable(@PathVariable String userid) {
		return usersService.disable(userid);
	
	}

	//직원 활성화
	@PutMapping("/enable/{userid}")
	public Map<String, String> enable(@PathVariable String userid) {
		return usersService.enable(userid);
	
	}
}
