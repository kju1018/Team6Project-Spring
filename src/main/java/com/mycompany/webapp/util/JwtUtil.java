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
   //ë¹„ë??‚¤(?…¸ì¶œë˜ë©? ?•ˆ?¨)
   private static final String secretKey = "secret";
   
   //JWT ?ƒ?„±
   public static String createToken(String uid) {
      String token = null;
      try {
         token = Jwts.builder()
                     .setHeaderParam("typ", "JWT") //?† ?°?˜ ì¢…ë¥˜
                     .setHeaderParam("alg", "HS256") //?•”?˜¸?™” ?•Œê³ ë¦¬ì¦? ì¢…ë¥˜
                     .setExpiration(new Date(new Date().getTime() + (1000*60*60*12))) //?† ?°?˜ ?œ ?š¨ê¸°ê°„
                     .claim("uid", uid) //?† ?°?— ???¥?˜?Š” ?°?´?„°
                     .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")) //ë¹„ë??‚¤
                     .compact(); //ëª¨ë“  ?‚´?š©?„ ë¬¶ê¸°
      } catch(Exception e) {
         e.printStackTrace();
      }
      return token;
   }
   
   //JWTë¥? ?ŒŒ?‹±?•´?„œ uid ?–»ê¸?
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
   //JWT ?œ ?š¨?„± ê²??‚¬: ?œ ?š¨ê¸°ê°„ ?™•?¸ 29ë¶„ì— ?† ?° ë³´ëƒˆ?–´. 1ë¶? ?‚¨?•˜?–´. ê·¸ëŸ¼ ?–´?–»ê²? ?•´ì¤?? ?‹¤?‹œ ?† ?° ë°œí–‰?•´ì¤˜ì•¼ì§?. 
   public static boolean validateToken(String token) {
	   boolean validate = false;
	   try {
	         JwtParser parser = Jwts.parser();
	         parser.setSigningKey(secretKey.getBytes("UTF-8"));
	         Jws<Claims> jws = parser.parseClaimsJws(token);
	         Claims claims = jws.getBody();
	         validate = claims.getExpiration().after(new Date()); // ë§Œë£Œê°? ?•„ì§? ?•ˆ?˜?—ˆ?‹¤. ë§Œë£Œ?‹œê°„ì´ ?˜„?¬?‹œê°? 1ë¶? ?›„?´?‹¤.
//	         if(validate) {
//	        	 long remainTime = claims.getExpiration().getTime() - new Date().getTime();
//	        	 logger.info("" + remainTime/1000 + "ì´? ?‚¨?•˜?Šµ?‹ˆ?‹¤.");
//	         } ?‚˜ì¤‘ì— ?™œ?š©?•´ë³´ê¸°
	   } catch(Exception e) {
	         e.printStackTrace();
	      }
	   return validate;
   }
   
   
   //?…Œ?Š¤?Š¸
   public static void main(String[] args) {
      //?† ?° ?ƒ?„±
	  String jwt = createToken("user1");
	  System.out.println(jwt);
      logger.info(jwt);
      
      // 5ì´? ?”œ? ˆ?´
      try { Thread.sleep(5000); } catch(Exception e) {};
      
      //?† ?¬ ? •ë³? ?–»ê¸?
      if(validateToken(jwt)) {
    	  String uid = getUid(jwt);
    	  logger.info(uid);
      }
   }
}
