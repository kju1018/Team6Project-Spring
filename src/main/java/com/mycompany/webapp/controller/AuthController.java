package com.mycompany.webapp.controller;


import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.security.JwtUtil;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Resource(name="daoAuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/login")
	public Map<String, String> userlogin(@RequestBody Map<String, String> user) {
		String uid = user.get("userid");
		String upassword = user.get("userpassword");
		
		//사용자 인증하기
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(uid, upassword);
	    Authentication authentication = authenticationManager.authenticate(authReq);
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    
	    //JWT 토큰 생성
	    String authToken = JwtUtil.createToken(uid);
		
	    //JSON 응답 보내기
		Map<String, String> map = new HashMap<>();
		map.put("userid", uid);
		map.put("authToken", authToken);
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
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		logger.info(user.toString());
		return null;
		
	}

}