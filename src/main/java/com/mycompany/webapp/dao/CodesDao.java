package com.mycompany.webapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Code;

@Mapper
public interface CodesDao {
	public Code selectByCode(Code code);
}
