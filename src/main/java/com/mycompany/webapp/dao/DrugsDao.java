package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Drug;

@Mapper
public interface DrugsDao {
	public List<Drug> selectAll();
}
