package com.mycompany.webapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Autowired
	private UsersService usersService;
	
	//유저정보 가져오기 usertype => 의사 / 간호사/ 전체
	@GetMapping("/userlist")
	public List<User> UserList(String usertype) {
		
		List<User> list= usersService.getUserList(usertype);
		return list;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(@RequestBody User user) {
		
		usersService.join(user);
		return null;
		 
	}
}
