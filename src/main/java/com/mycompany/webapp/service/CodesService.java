package com.mycompany.webapp.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.CodesDao;
import com.mycompany.webapp.dto.Code;

@Service
public class CodesService {
	
	@Autowired
	private CodesDao codeDao;

	private static final Logger logger =
			LoggerFactory.getLogger(CodesService.class);
	
	public Map<String, Object> codeLogin(Code code) {
		Code existCode = codeDao.selectByCode(code);
		Map<String, Object> map = new HashMap<String, Object>();
		if(existCode != null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			if(bpe.matches(code.getPassword(), existCode.getPassword())) {
				map.put("codenumber", existCode.getCodenumber());
				map.put("hospitalname", existCode.getHospitalname());
				map.put("state", "success");
			} else {
				map.put("state", "passwordErr");
			}
		} else {
			map.put("state", "numberErr");
			
		}
		logger.info(map.toString());
		
		return map;
	}
}
