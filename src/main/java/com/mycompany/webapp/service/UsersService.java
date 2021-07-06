package com.mycompany.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.User;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;

	public void join(User user) {
		usersDao.insert(user);
	}
	public String login(User user) {
		// TODO Auto-generated method stub
		User dbUser = usersDao.selectByUid(user.getUid());
		if(dbUser == null) {
			return "wrongUid";
			
		}else{
			if(!dbUser.getUpassword().equals(user.getUpassword())) {
				return "wrongUpassword";				
			}
			else {
				return "success";				
			}
		
		}
		
	}
	/*	@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			User dbUser = usersDao.selectByUid(username);
			UserDetails loginUser = null;
			java.util.List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(dbUser.getUauthority()));
			loginUser = new org.springframework.security.core.userdetails.User(dbUser.getUid(), dbUser.getUpassword() ,authorities);
			System.out.println("load" + loginUser.getAuthorities());
			return loginUser;
		}*/

}
