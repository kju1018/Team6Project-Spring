package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ChattingsHistoryDao;
import com.mycompany.webapp.dao.ReceptionsDao;
import com.mycompany.webapp.dto.ChattingHistory;
import com.mycompany.webapp.dto.ReceptedTestDataParameter;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Treatment;


@Service
public class ChattingsHistoryService {
	@Autowired
	private ChattingsHistoryDao chattinghistoryDao;
	
	
	//채팅기록 등록
	public int PushChattingHistory(ChattingHistory chattinghistory) {
		return chattinghistoryDao.insert(chattinghistory);
	}
	//전체 채팅기록 가져오기
	public List<ChattingHistory> GetAllChattingHistory(){
		return chattinghistoryDao.selectAll();
	}
	
	//특정 채팅기록 가져오기
	public ChattingHistory GetChattingHistory(String userid) {
		return chattinghistoryDao.selectByChatId(userid);
	}
	//특정 채팅 기록 삭제하기
	public int RemoveChattingHistory(String userid) {
		return chattinghistoryDao.deleteByChatId(userid);
	}
	

}
