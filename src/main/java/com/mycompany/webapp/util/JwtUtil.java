package com.mycompany.webapp.util;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
   private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
   //λΉλ??€(?ΈμΆλλ©? ??¨)
   private static final String secretKey = "secret";
   
   //JWT ??±
   public static String createToken(String uid) {
      String token = null;
      try {
         token = Jwts.builder()
                     .setHeaderParam("typ", "JWT") //? ?°? μ’λ₯
                     .setHeaderParam("alg", "HS256") //??Έ? ?κ³ λ¦¬μ¦? μ’λ₯
                     .setExpiration(new Date(new Date().getTime() + (1000*60*60*12))) //? ?°? ? ?¨κΈ°κ°
                     .claim("uid", uid) //? ?°? ???₯?? ?°?΄?°
                     .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")) //λΉλ??€
                     .compact(); //λͺ¨λ  ?΄?©? λ¬ΆκΈ°
      } catch(Exception e) {
         e.printStackTrace();
      }
      return token;
   }
   
   //JWTλ₯? ??±?΄? uid ?»κΈ?
   public static String getUid(String token) {
      String uid = null;
      try {
         JwtParser parser = Jwts.parser();
         parser.setSigningKey(secretKey.getBytes("UTF-8"));
         Jws<Claims> jws = parser.parseClaimsJws(token);
         Claims claims = jws.getBody();
         uid = claims.get("uid", String.class);
      } catch(Exception e) {
         e.printStackTrace();
      }
      return uid;
   }
   //JWT ? ?¨?± κ²??¬: ? ?¨κΈ°κ° ??Έ 29λΆμ ? ?° λ³΄λ?΄. 1λΆ? ?¨??΄. κ·ΈλΌ ?΄?»κ²? ?΄μ€?? ?€? ? ?° λ°ν?΄μ€μΌμ§?. 
   public static boolean validateToken(String token) {
	   boolean validate = false;
	   try {
	         JwtParser parser = Jwts.parser();
	         parser.setSigningKey(secretKey.getBytes("UTF-8"));
	         Jws<Claims> jws = parser.parseClaimsJws(token);
	         Claims claims = jws.getBody();
	         validate = claims.getExpiration().after(new Date()); // λ§λ£κ°? ?μ§? ????€. λ§λ£?κ°μ΄ ??¬?κ°? 1λΆ? ??΄?€.
//	         if(validate) {
//	        	 long remainTime = claims.getExpiration().getTime() - new Date().getTime();
//	        	 logger.info("" + remainTime/1000 + "μ΄? ?¨??΅??€.");
//	         } ?μ€μ ??©?΄λ³΄κΈ°
	   } catch(Exception e) {
	         e.printStackTrace();
	      }
	   return validate;
   }
   
   
   //??€?Έ
   public static void main(String[] args) {
      //? ?° ??±
	  String jwt = createToken("user1");
	  System.out.println(jwt);
      logger.info(jwt);
      
      // 5μ΄? ?? ?΄
      try { Thread.sleep(5000); } catch(Exception e) {};
      
      //? ?¬ ? λ³? ?»κΈ?
      if(validateToken(jwt)) {
    	  String uid = getUid(jwt);
    	  logger.info(uid);
      }
   }
}
