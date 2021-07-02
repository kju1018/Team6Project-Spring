package com.mycompany.webapp.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter  { // ?�� ?��?�� 추상 ?��?��?��; �? 말�? 추상 메소?���? ?��?��?�� �?, ?��?���? ?��?���? ?��?��?���? ?��?��
	
//	private UserDetailsService userDetailsService; // ?��?��?��?�� ?��?�� ?��보�?? �?�?�? ?��?�� ?��비스 
//	public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//JWT ?��근이 ?���? ?��?���? ?��?��?�� 경우 //  
		HttpServletRequest httpRequest = (HttpServletRequest) request; // ServletRequest?�� 메서?���? ?��?���?�? HttpServletRequest�? 바꿔줘야
		
		String path = httpRequest.getRequestURL().toString();
		
		String jwt = httpRequest.getHeader("authToken"); // ?���? ?��리�? ?��?��?��?��?��. authToken?���? ?��?��?���?
		if(jwt == null) {
			if(!path.equals("http://localhost:8080/Auth/login")) {
				
			}
			// JWT�? ?���? ?��?��미터�? ?��?��?�� 경우 ex) <img src="download?bno=3"/> + authToken="xxxx"�? ?��겨줘?��
			jwt = request.getParameter("authToken");
		} 
		if(jwt != null) { // ?��?��?���? ?��?��?��
			if(JwtUtil.validateToken(jwt)) { // ?��?��???�� 만료기간?�� ?��?���? ?��?��
				//uid�? 뽑아?��?�� ?��?��?�� ?��?��
				//JWT?��?�� uid ?���?
				String uid = JwtUtil.getUid(jwt); // ?��?��?�� ?��?��?�� 경우 uid ?��?��???�� 
				System.out.println(uid);
				// DB?��?�� uid?�� ?��?��?��?�� ?��보�?? �??��?���?(?���?, 권한?��)
				//UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
				//최종 ?���? 객체�? 만들?��?��
				//?���? ?���? 객체, ?��증이 ?��공되�? authentication 객체�? 만들?���?, ?���? 객체�? 만들고나?�� 
				//Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
				// ?��?���? ?��?��리티?�� ?��록을 ?��?��겁니?��..?�� ?��?��?�� 로그?��?��?��. 
				//SecurityContextHolder.getContext().setAuthentication(authentication); // ?��?��?��?���? ?��?��?�� ?��?��?��... ?��?��링시?��리티�? ?��증객체�?? ?��?��?��?���? �?리한?��?�� �?.
				//System.out.println(SecurityContextHolder.getContext().getAuthentication());
				//jwt ?��?��?�� uid�? ?��?��?���?? jwt�? 조사?��보니�? ?��?��?��.. 그게 무슨말이?���? 
				// 복호?��?��보니�? ?��짜�? ?��?��?���? �? ?��?�� uid�? ?��?��.
				// 그러�? ?��?��링시?��리티?�� �? ?��체로 ?��?��?�� ?��줘야?��?��?��거야. 그걸 받게?���? ?��?��?��
				// ?��?��로그?�� ?��줘야�?.  
				
			}
		}
		chain.doFilter(httpRequest, response);
	} 
	
}
