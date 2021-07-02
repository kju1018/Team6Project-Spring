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
   //비�??��(?��출되�? ?��?��)
   private static final String secretKey = "secret";
   
   //JWT ?��?��
   public static String createToken(String uid) {
      String token = null;
      try {
         token = Jwts.builder()
                     .setHeaderParam("typ", "JWT") //?��?��?�� 종류
                     .setHeaderParam("alg", "HS256") //?��?��?�� ?��고리�? 종류
                     .setExpiration(new Date(new Date().getTime() + (1000*60*60*12))) //?��?��?�� ?��?��기간
                     .claim("uid", uid) //?��?��?�� ???��?��?�� ?��?��?��
                     .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")) //비�??��
                     .compact(); //모든 ?��?��?�� 묶기
      } catch(Exception e) {
         e.printStackTrace();
      }
      return token;
   }
   
   //JWT�? ?��?��?��?�� uid ?���?
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
   //JWT ?��?��?�� �??��: ?��?��기간 ?��?�� 29분에 ?��?�� 보냈?��. 1�? ?��?��?��. 그럼 ?��?���? ?���?? ?��?�� ?��?�� 발행?��줘야�?. 
   public static boolean validateToken(String token) {
	   boolean validate = false;
	   try {
	         JwtParser parser = Jwts.parser();
	         parser.setSigningKey(secretKey.getBytes("UTF-8"));
	         Jws<Claims> jws = parser.parseClaimsJws(token);
	         Claims claims = jws.getBody();
	         validate = claims.getExpiration().after(new Date()); // 만료�? ?���? ?��?��?��?��. 만료?��간이 ?��?��?���? 1�? ?��?��?��.
//	         if(validate) {
//	        	 long remainTime = claims.getExpiration().getTime() - new Date().getTime();
//	        	 logger.info("" + remainTime/1000 + "�? ?��?��?��?��?��.");
//	         } ?��중에 ?��?��?��보기
	   } catch(Exception e) {
	         e.printStackTrace();
	      }
	   return validate;
   }
   
   
   //?��?��?��
   public static void main(String[] args) {
      //?��?�� ?��?��
	  String jwt = createToken("user1");
	  System.out.println(jwt);
      logger.info(jwt);
      
      // 5�? ?��?��?��
      try { Thread.sleep(5000); } catch(Exception e) {};
      
      //?��?�� ?���? ?���?
      if(validateToken(jwt)) {
    	  String uid = getUid(jwt);
    	  logger.info(uid);
      }
   }
}
