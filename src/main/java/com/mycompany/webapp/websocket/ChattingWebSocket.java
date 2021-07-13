package com.mycompany.webapp.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChattingWebSocket extends TextWebSocketHandler {
	private static final Logger logger = 
			LoggerFactory.getLogger(ChattingWebSocket.class);
	private List<Client> clients = new ArrayList<Client>();
	
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
		System.out.println("handle!!");
		for(Client client : clients) {
			if(header.equals("HELLO")) {
				System.out.println("enter!!");
				jsonObject.put("header", "HELLO");
				jsonObject.put("from", from);
				jsonObject.put("message", from+"님이 입장하셨습니다.");
				client.session.sendMessage(new TextMessage(jsonObject.toString()));
			}else if(header.equals("CHATTING")){
				client.session.sendMessage(message);	
			}
			
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed: " + session.getId());
		Iterator<Client> iterator = clients.iterator();
		while(iterator.hasNext()) {
			Client client = iterator.next();
			if(client.session.getId() == session.getId()) {
				client.session.close();
				iterator.remove();
			}
		}
	}
	

}
