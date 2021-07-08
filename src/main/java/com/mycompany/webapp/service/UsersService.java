package com.mycompany.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.User;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;

	public void join(User user) {
		
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setUserpassword(bpe.encode(user.getUserpassword()));
		
		user.setUserenabled(1);
		user.setCodenumber(1);
		
		usersDao.insert(user);

	}

}
