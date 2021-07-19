package com.mycompany.webapp.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisWebSocket extends TextWebSocketHandler 
                            implements MessageListener {
	private static final Logger logger = 
			LoggerFactory.getLogger(RedisWebSocket.class);
	private List<Client> clients = new ArrayList<Client>();
	
	class Client {
		WebSocketSession session;
		String topic;
		Client(WebSocketSession session) { this.session = session; }
		void setTopic(String topic) { this.topic = topic; }
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
		//logger.info("afterConnectionEstablished: " + session.getId());
		Client client = new Client(session);
		clients.add(new Client(session));
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String strJson = message.getPayload();
		JSONObject jsonObject = new JSONObject(strJson); 
		String topic = jsonObject.getString("topic");
		//logger.info("handleTextMessage: " + topic);
		for(Client client : clients) {
			if(client.session.getId() == session.getId()) {
				client.topic = topic;
				break;
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//logger.info("afterConnectionClosed: " + session.getId());
		Iterator<Client> iterator = clients.iterator();
		while(iterator.hasNext()) {
			Client client = iterator.next();
			if(client.session.getId() == session.getId()) {
				client.close();
				iterator.remove();
			}
		}
	}
	
	//Redis 메시지 구독(Subscribe) 메소드
	@Override
	public void onMessage(Message message, byte[] pattern) {
		//logger.info(message.toString());
		pushMessage(message);
	}	
	
	private void pushMessage(Message message) {
		String topic =  new String(message.getChannel());
		TextMessage messages= new TextMessage(message.getBody()); 
		for(Client client : clients) {
			try {
				if(client.topic.endsWith("#")) {
					String t = client.topic.split("#")[0];
					if(topic.startsWith(t)) {
						client.session.sendMessage(messages);
					}
				} else {
					if(topic.equals(client.topic)) {
						client.session.sendMessage(messages);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
