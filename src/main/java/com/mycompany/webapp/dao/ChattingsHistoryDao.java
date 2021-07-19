package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.ChattingHistory;
@Mapper
public interface ChattingsHistoryDao {

	//채팅기록 등록
	public int insert(ChattingHistory chattinghistory);
	//전체 채팅기록 가져오기
	public List<ChattingHistory> selectAll();
	
	//특정 채팅기록 가져오기
	public ChattingHistory selectByChatId(String userid);
	//특정 채팅 기록 삭제하기
	public int deleteByChatId(String userid);
}
