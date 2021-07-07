package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.User;

@Mapper
public interface UsersDao {
	public List<User> selectAll(String usertype);
}
