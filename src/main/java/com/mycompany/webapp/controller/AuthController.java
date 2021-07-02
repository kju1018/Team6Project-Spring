package com.mycompany.webapp.controller;


import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.User;

import com.mycompany.webapp.service.UsersService;
import com.mycompany.webapp.util.JwtUtil;

@RestController
@RequestMapping("/Auth")
public class AuthController {
	@Autowired
	private UsersService usersService;

	@PostMapping(value="/login")
	public Map<String, String> login(@RequestBody Map<String,Object> json,HttpSession session, Authentication auth) {
		
		User user = new User();
		user.setUid(json.get("uid").toString());
		user.setUpassword(json.get("upassword").toString());
		String result = usersService.login(user); 
	      Map<String, String> map = new HashMap<String, String>();
		if(result.equals("success")) {
			String jwt = JwtUtil.createToken(user.getUid());
		      map.put("status", "success");
		      map.put("uid", user.getUid());
		      map.put("authToken", jwt);
		}else {
			map.put("status", result);
		}
		return map;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		return "success";
	}	
	@GetMapping("/test")
	public String test(HttpSession session) {
	
		return "success";
	}	


}