package com.mycompany.webapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.User;

@Mapper
public interface UsersDao {
	public int insert(User user);

	public User selectByUserid(String userid);
	public User selectAll();
}
