package com.mycompany.webapp.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.dto.ChattingConnectionInfo;

@Component
public class ChattingWebSocket extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(ChattingWebSocket.class);
	
	//클라이언트 세션 목록
	private List<Client> clients = new ArrayList<Client>();
	//커넥션 맵 <세션키, ChattingConnectionInfo>
	private Map<String,ChattingConnectionInfo> ConnectionMap = new LinkedHashMap<>(); 
	
	
	class Client {
		WebSocketSession session;
		Client(WebSocketSession session) { this.session = session; }
		void close() {
			try {
				session.close();
			} catch (IOException e) {
			}
		}
	}	
	
	//WebSocket ----------------------------------------------------------------------
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished: " + session.getId());
		Client client = new Client(session);
		clients.add(new Client(session));
		Iterator<Client> iterator = clients.iterator();
		while(iterator.hasNext()) {
			Client cl = iterator.next();
			System.out.println(cl.session.getId());
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String strJson = message.getPayload();
		JSONObject jsonObject = new JSONObject(strJson);
		String header = jsonObject.getString("header");
		String from = jsonObject.getString("from");
		String content = jsonObject.getString("message");
		Object[] array=null;
		
			//접속 패킷 받았을때
			if(header.equals("HELLO")) {
				System.out.println("HELLO");
				ObjectMapper mapper = new ObjectMapper();
				//받은 정보를 connectionInfo로 변환
				ChattingConnectionInfo connectionInfo = mapper.readValue(jsonObject.get("connectioninfo").toString(),ChattingConnectionInfo.class );
				//받은 연결정보를 커넥션맵에 수정
				ConnectionMap.put(session.getId(), connectionInfo);
				//커넥션맵을 Array시킴
				array = ConnectionMap.values().toArray();
				//연결정보를 JSONObject에 추가
				jsonObject.put("connectionlist", array);
				
				
				
				for(Client client : clients) {
					client.session.sendMessage(new TextMessage(jsonObject.toString()));
				}
			}
			//자리 비움패킷 받았을때
            else if(header.equals("BRB")){
            	ObjectMapper mapper = new ObjectMapper();
    			//받은 정보를 connectionInfo로 변환
    			ChattingConnectionInfo connectionInfo = mapper.readValue(jsonObject.get("connectioninfo").toString(),ChattingConnectionInfo.class );
    			//받은 연결정보를 커넥션맵에 수정
    			ConnectionMap.put(session.getId(), connectionInfo);
    			//커넥션맵을 Array시킴
    			array = ConnectionMap.values().toArray();
    			//연결정보를 JSONObject에 추가
    			jsonObject.put("connectionlist", array);
            	
            	
            	for(Client client : clients) {
					client.session.sendMessage(new TextMessage(jsonObject.toString()));
				}
            }
            //접속종료패킷 받았을때
            else if(header.equals("BYE")){
            	
            	
            	
            	
            	System.out.println("btye"+ jsonObject.toString());
            	
            	
            	ObjectMapper mapper = new ObjectMapper();
    			//받은 정보를 connectionInfo로 변환
    			ChattingConnectionInfo connectionInfo = mapper.readValue(jsonObject.get("connectioninfo").toString(),ChattingConnectionInfo.class );
    			//받은 연결정보를 커넥션맵에 수정
    			ConnectionMap.put(session.getId(), connectionInfo);
    			//커넥션맵을 Array시킴
    			array = ConnectionMap.values().toArray();
    			//연결정보를 JSONObject에 추가
    			jsonObject.put("connectionlist", array);
            	for(Client client : clients) {
					client.session.sendMessage(new TextMessage(jsonObject.toString()));
				}
            }
			//채팅 패킷 받았을때
			else if(header.equals("CHATTING")){
				System.out.println("CHATTING");
				for(Client client : clients) {
					client.session.sendMessage(message);
				}	
			}
			
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed: " + session.getId());
		//커넥션맵에서 삭제
		ConnectionMap.remove(session.getId());
		//커넥션맵을 Array시킴
		Object[] array = ConnectionMap.values().toArray();
		
		JSONObject jsonObject = new JSONObject();
		//헤더 설정
		jsonObject.put("header", "BYE");
		//연결정보를 JSONObject에 추가
		jsonObject.put("connectionlist", array);
		System.out.println(jsonObject);
		
		
		
		Iterator<Client> iterator = clients.iterator();
		while(iterator.hasNext()) {
			Client client = iterator.next();
			if(client.session.getId() == session.getId()) {
				client.session.close();
				iterator.remove();
			}
			else {
				
				client.session.sendMessage(new TextMessage(jsonObject.toString()));
			}
		}
	}
	

}
