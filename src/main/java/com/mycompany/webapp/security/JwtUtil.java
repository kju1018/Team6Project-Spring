package com.mycompany.webapp.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	//비밀키(누출되면 안됨)
    private static final String secretKey = "secret";

    // JWT 토큰 생성: 사용자 아이디 포함
    public static String createToken(String uid) {
		String token = null;
		try {
			/*
			token = Jwts.builder()
				//헤더(header) 설정(토큰 타입, 해싱 알고리즘 종류)
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("alg", "HS256")
				//내용(payload) 설정
				.setExpiration(new Date(new Date().getTime() + 1000*60*60*12))
				.claim("uid", uid)
				//서명(signature) 설정
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
				//헤더.내용.서명 형태로 묶기
				.compact();
			*/
			JwtBuilder builder = Jwts.builder();
			builder.setHeaderParam("typ", "JWT"); //토큰의 종류
			builder.setHeaderParam("alg", "HS256"); //암호화 알고리즘 종류
			builder.setExpiration(new Date(new Date().getTime() + 1000*60*60*12)); //토큰의 유효기간
			builder.claim("uid", uid); //토큰에 저장되는 데이터
			builder.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")); //비밀키
			token = builder.compact(); //모든 내용을 묶기
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
    }
    
    //JWT 토큰에서 모든 내용(Claims) 얻기
    public static Claims getClaims(String token) {
    	Claims claims = null;
		try {
			/*
			claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes("UTF-8"))
				.parseClaimsJws(token)
				.getBody();
			*/
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretKey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			claims = jws.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claims;
    }

	//JWT 토큰에서 사용자 아이디 얻기
	public static String getUid(String token) {
		String uid = null;
		try {
			/*
			Claims claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes("UTF-8"))
				.parseClaimsJws(token)
				.getBody();
			uid = claims.get("uid", String.class);
			*/
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretKey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			uid = claims.get("uid", String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uid;
	}
	
	//JWT 토큰 유효성 검사: 만료일자 확인 
	public static boolean validateToken(String token) {
		boolean validate = false;
		try {
			/*
			Claims claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes("UTF-8"))
				.parseClaimsJws(token)
				.getBody();
			*/
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretKey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			validate = !claims.getExpiration().before(new Date());
			/*if(validate) {
				long remainMillSecs = claims.getExpiration().getTime() - new Date().getTime();
				logger.info("" + remainMillSecs/1000 + "초 남았음");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validate;
	}
	
	//테스트
	public static void main(String[] args) {
		//토큰 생성
		String jwt = createToken("user1");
		logger.info(jwt);
		
		//5초 지연
		try { Thread.sleep(5000); } catch(Exception e) {};
		
		//토큰 정보 얻기
		if(validateToken(jwt)) {
			String uid = getUid(jwt);
			logger.info(uid);
		}
	}	
}
