package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Notice;

@Mapper
public interface NoticesDao {
	//공지데이터 삽입
	public int insert(Notice notices);
	//공지삭제
	public int deleteNotice(int noticeid);
	//전체공지 가져오기
	public List<Notice> selectAll();
	//공지 선택
	public Notice selectBytitle(int noticeid);
}
