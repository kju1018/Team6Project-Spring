package com.mycompany.webapp.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


import io.jsonwebtoken.Claims;

//@Component를 붙이지 않는 이유는 이것을 붙이게되면 
//GenericFilterBean을 상속한 Filter는 자동으로 필터체인에 추가되기 때문에
//두번 호출된다.
public class JwtAuthenticationFilter extends GenericFilterBean {
	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//JWT 토큰이 요청 헤더로 전송된 경우
		String jwt = ((HttpServletRequest) request).getHeader("authToken");
		if(jwt == null || jwt.trim().equals("")) {
			//JWT가 요청 파라미터 전달된 경우
			//<img src="download?bno=3&authToken=xxxx"/>
			jwt = request.getParameter("authToken");
		}
		if(jwt != null && !jwt.trim().equals("")) {
		    //유효한 JWT 토큰인지 확인
			if(JwtUtil.validateToken(jwt)) {
				//JWT에서 uid 얻기
				String uid = JwtUtil.getUid(jwt);
				//DB에서 uid에 해당하는 정보를 가져오기(이름, 권한(롤)들)
				UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
				//인증 객체 생성
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
				//Spring Securty에 인증 객체 등록
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
    	
        chain.doFilter(request, response);
    }  
}
