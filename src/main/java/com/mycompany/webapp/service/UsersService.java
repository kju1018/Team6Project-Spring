package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.dto.User;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;

	public Map<String, String> join(User user) {
		
		Map<String, String> map = new HashMap<String, String>();
		User existUser = usersDao.selectByUserid(user.getUserid());
		if(existUser == null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			user.setUserpassword(bpe.encode(user.getUserpassword()));
			user.setUserenabled(1);
			usersDao.insert(user);
			map.put("state", "success");
		} else {
			map.put("state", "failure");
		}

		return map;
	}
	public List<User> getUserList(String usertype) {
		return usersDao.selectAll(usertype);
	}
	
	public User getUser(String userid) {
		return usersDao.selectByUserid(userid);
	}
	
	public Map<String, String> updateUser(User user) {
		Map<String, String> map = new HashMap<String, String>();
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setUserpassword(bpe.encode(user.getUserpassword()));
		usersDao.updateUser(user);
		map.put("state", "success");
		
		return map;
	}

	public Map<String, String> deletUser(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		int row = usersDao.deleteUser(userid);
		if(row > 0) {
			map.put("state", "success");
			
		} else {
			map.put("state", "failure");
		}
		
		return map;
	}
	
	public Map<String, String> disable(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		int row = usersDao.disableUser(userid);
		if(row > 0) {
			map.put("state", "success");
			
		} else {
			map.put("state", "failure");
		}
		
		return map;
	}
	
	public Map<String, String> enable(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		int row = usersDao.enableUser(userid);
		if(row > 0) {
			map.put("state", "success");
			
		} else {
			map.put("state", "failure");
		}
		
		return map;
	}

}
