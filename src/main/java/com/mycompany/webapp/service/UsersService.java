package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.dto.User;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;

	public List<User> getUserList(String usertype) {
		return usersDao.selectAll(usertype);
	}
//	public String login(User user) {
//		// TODO Auto-generated method stub
//		User dbUser = usersDao.selectByUid(user.getUserid());
//		if(dbUser == null) {
//			return "wrongUid";
//			
//		}else{
//			if(!dbUser.getUpassword().equals(user.getUpassword())) {
//				return "wrongUpassword";				
//			}
//			else {
//				return "success";				
//			}
//		
//		}
//		
//	}

}
