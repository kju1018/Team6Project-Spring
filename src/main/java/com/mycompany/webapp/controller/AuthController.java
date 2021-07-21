package com.mycompany.webapp.controller;


import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Code;
import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.security.JwtUtil;
import com.mycompany.webapp.service.CodesService;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Resource(name="daoAuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CodesService codesService;
	
	@PostMapping("/login")
	public Map<String, Object> userlogin(@RequestBody Map<String, String> user) {
		String userid = user.get("userid");
		String upassword = user.get("userpassword");
		int codenumber = Integer.parseInt(user.get("codenumber"));
		logger.info(userid);
		logger.info(upassword);
		logger.info(codenumber+"");
		//사용자 인증하기
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userid, upassword);
	    Authentication authentication = authenticationManager.authenticate(authReq);
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    
	    //JSON 응답 보내기
		Map<String, Object> map = new HashMap<>();
	    //로그인 유저 정보 가져오기
	    User newUser = usersService.getUser(userid);
	    
	    //해당 병원 계정이 아닐경우
	    if(newUser.getCodenumber() != codenumber) {
	    	map.put("state", "err_nullAuth");
	    	return map;
	    }
	    
	    //JWT 토큰 생성
	    String authToken = JwtUtil.createToken(userid);

		map.put("state", "success");
		map.put("userid", userid);
		map.put("authToken", authToken);
		map.put("role_authority", newUser.getRole_authority());
		map.put("username", newUser.getUsername());
		map.put("codenumber", newUser.getCodenumber());
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
	
//	@PostMapping("/codelogin")
//	public Map<String, Object> codelogin(@RequestBody Code code){
//		
//		return codesService.codeLogin(code);
//	}
	
}