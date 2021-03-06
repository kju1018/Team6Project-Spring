package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.User;

@Mapper
public interface UsersDao {

	public int insert(User user);
	public User selectByUserid(String userid);
	public User selectAll();
	public List<User> selectAll(String usertype);
	public int updateUser(User user);
	public int deleteUser(String userid);
	public int disableUser(String userid);
	public int enableUser(String userid);
}
