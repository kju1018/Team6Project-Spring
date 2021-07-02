package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins="*")
@Controller
public class HomeController {
	private static final Logger logger = 
			LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	@RequestMapping("/sendRedisMessage")
	public void sendRedisMessage(String topic, String content, HttpServletResponse res) {
		logger.info("sendMessage");
		try {
			redisTemplate.convertAndSend(topic, content);
		
			JSONObject json = new JSONObject();
			json.put("result", "success");
			res.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = res.getWriter();
			writer.write(json.toString());
			writer.flush();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
