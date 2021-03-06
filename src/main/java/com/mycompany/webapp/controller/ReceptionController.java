package com.mycompany.webapp.controller;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.dto.ChattingHistory;
import com.mycompany.webapp.dto.Diagnoses;
import com.mycompany.webapp.dto.Drug;
import com.mycompany.webapp.dto.Patient;
import com.mycompany.webapp.dto.ReceptedTestDataParameter;
import com.mycompany.webapp.dto.Reservation;
import com.mycompany.webapp.dto.Test;
import com.mycompany.webapp.dto.TestData;
import com.mycompany.webapp.dto.TestReception;
import com.mycompany.webapp.dto.Treatment;
import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.service.ChattingsHistoryService;
import com.mycompany.webapp.service.DiagnosesService;
import com.mycompany.webapp.service.DrugsService;
import com.mycompany.webapp.service.PatientsService;
import com.mycompany.webapp.service.ReceptionsService;
import com.mycompany.webapp.service.ReservationsService;
import com.mycompany.webapp.service.TreatmentsService;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/reception")

public class ReceptionController {
	@Autowired
	private RedisTemplate<String, List<Map<String,Object>>> redisTemplate;
	@Autowired
	ReservationsService reservationservice;
	@Autowired
	PatientsService patientservice; 
	@Autowired
	DrugsService drugsService;
	@Autowired
	DiagnosesService diagnosesService;
	@Autowired
	ReceptionsService receptionsService;
	@Autowired
	TreatmentsService treatmentsService;
	@Autowired
	UsersService usersService;
	@Autowired
	ChattingsHistoryService chattingsHistoryService;
	
	//?????? ???????????? ????????????
	@GetMapping("/reservationlist")
	public List<Reservation> ReservationList() {
		List<Reservation> list= reservationservice.getReservationList();	
		return list;
	}
	//???????????? ????????????
	@PostMapping("/registerreservation")
	public Reservation RegisterReservation(@RequestBody List<Object> obj) {
		
		//????????? ?????? ?????????
		ObjectMapper mapper = new ObjectMapper();
		Reservation newreservation=null;
		List<Test> testlist = null;
		try {
			newreservation = mapper.readValue(mapper.writeValueAsString(obj.get(0)), Reservation.class);
			reservationservice.RegisterReservation(newreservation);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newreservation;
	}
	//???????????? ????????????
	@DeleteMapping("/removereservation")
	public int RemoveReservation(@RequestBody Map<String,Integer> obj) {
		return reservationservice.RemoveReservation(obj.get("reservationid"));
	}
	//???????????? ????????????
	@PutMapping("/updatereservation")
	public int UpdateReservation(@RequestBody Reservation reservation) {
		reservationservice.UpdateReservation(reservation);
		return reservation.getReservationid();
	}
	
	
	//?????? ???????????? ????????????
	@GetMapping("/patientlist")
	public List<Patient> PatientList() {
		List<Patient> list= patientservice.getPatientList();	
		return list;
	}
	//?????? ?????? ????????????
	@GetMapping("/getpatient")
	public Patient getPatient(int patientid) {
		return	patientservice.getPatient(patientid);	
		 
	}
	//??????????????????
	@PostMapping("/registerpatient")
	public int RegisterPatient(@RequestBody Patient patient) {
		patientservice.RegisterPatient(patient);
		return patient.getPatientid();
	}
	//??????????????????
	@PutMapping("/updatepatient")
	public int UpdatePatient(@RequestBody Patient patient) {
		patientservice.UpdatePatient(patient);
		return patient.getPatientid();
	}
	
	
	//?????? ???????????? ???????????? + ????????? ????????? ??????
	@GetMapping("/treatments/{patientid}")
	public Map<String,Object> TreatmentList(@PathVariable String patientid) {
		List<Treatment> treatmentlist =  treatmentsService.getTreatments(patientid);
		List<User>treatmentuserlist = new ArrayList<>();
		for(int i=0; i<treatmentlist.size(); i++) {
			User user = usersService.getUser(treatmentlist.get(i).getUserid());
			treatmentuserlist.add(user);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("treatmentlist", treatmentlist );
		map.put("userlist", treatmentuserlist);
		return map;
	}
	
	//?????? ????????? ???????????? ????????????
	@GetMapping("/treatmentdetail")
	public Map<String,Object> TreatmentDetail(int treatmentid) {
		List<Drug> drugList = drugsService.getDrugsByTreatmentId(treatmentid);	
		List<Diagnoses> diagnosesList = diagnosesService.getDiagnosesByTreatmentId(treatmentid);
		
		List<String> PrescriptionTestDataIdList = receptionsService.GetPrescriptionTestDataByTreatmentid(treatmentid);
		List<TestData> PrescriptionTestDataList = new ArrayList<TestData>();
		for(int i=0; i<PrescriptionTestDataIdList.size(); i++) {
			TestData testdata = receptionsService.GetTestData(PrescriptionTestDataIdList.get(i));
			PrescriptionTestDataList.add(testdata);
		}
		List<Object> list = new ArrayList<Object>();
		list.add(diagnosesList);
		list.add(drugList);
		list.add(PrescriptionTestDataList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("treatmentdetail", list);
		return map;
	}
	
	//?????? ????????? ?????????????????? ?????? ???????????? ?????? ??????????????? ????????????
	@GetMapping("/prescriptiontest")
	public Map<String,Object> PrescriptionTest(int patientid) {
	
		List<String> PrescriptionTestDataIdList = receptionsService.GetPrescriptionTestDataByPatientid(patientid);
		List<TestData> PrescriptionTestDataList = new ArrayList<TestData>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		// ????????? ???????????? ????????? ????????????
		if(PrescriptionTestDataIdList.size()<1) {return map;}
		
		
		for(int i=0; i<PrescriptionTestDataIdList.size(); i++) {
			TestData testdata = receptionsService.GetTestData(PrescriptionTestDataIdList.get(i));
			PrescriptionTestDataList.add(testdata);
		}
		//?????? ????????? ??????????????? ??????????????? ?????????
		Collections.sort(PrescriptionTestDataList,(t1, t2)-> t2.getGroupcode().length() - t1.getGroupcode().length() );
		List<TestData> list = new ArrayList<TestData>();
		String groupcode = PrescriptionTestDataList.get(0).getGroupcode();
		for(TestData t : PrescriptionTestDataList){
			if(!groupcode.equals(t.getGroupcode())) {
				map.put(groupcode, list);
				groupcode = t.getGroupcode();
				list = new ArrayList<TestData>();
			}
			list.add(t);
		}
		map.put(groupcode, list);

		return map;
	}
	
	//?????? ????????????
	@PostMapping("/receptiontreatment")
	public Treatment ReceptionTreatment(@RequestBody Treatment treatment) {
		Treatment newtreatment =treatmentsService.create(treatment);
		return newtreatment ;
	}
	//??????????????? ???????????????????????????
	@GetMapping("/treatmentlist")
	public Map<String,Object> TreatmentList() {
		List<Treatment> treatmentlist= receptionsService.GetTreatmentData();
		List<User>treatmentuserlist = new ArrayList<>();
		List<Patient>treatmentpatientlist = new ArrayList<>();
		for(int i=0; i<treatmentlist.size(); i++) {
			User user = usersService.getUser(treatmentlist.get(i).getUserid());
			Patient patient = patientservice.getPatient(treatmentlist.get(i).getPatientid());
			treatmentuserlist.add(user);
			treatmentpatientlist.add(patient);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("treatmentlist", treatmentlist );
		map.put("userlist", treatmentuserlist);
		map.put("patientlist", treatmentpatientlist);
		return map;
	}
	//?????? ????????? ????????? ??????????????? ????????????
	@GetMapping("/testreceptionlist")
	public Map<String,Object> TestReceptionList() {
		List<TestReception> testlist= receptionsService.GetTestReceptionData();
		List<Patient>treatmentpatientlist = new ArrayList<>();
		for(int i=0; i<testlist.size(); i++) {
			Patient patient = patientservice.getPatient(testlist.get(i).getPatientid());
			treatmentpatientlist.add(patient);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("testlist", testlist );
		map.put("patientlist", treatmentpatientlist);
		return map;
	}
	//?????? ????????????
	@PostMapping("/receptiontest")
	public TestReception ReceptionTest(@RequestBody Map<String,Object>json) {
		int patientid = Integer.parseInt(json.get("patientid").toString());
		//TestReception?????????
		TestReception testreception = new TestReception();
		testreception.setTestdate(new Date());
		testreception.setPatientid(patientid);
		testreception.setStatus("?????????");
		testreception.setResultstatus("?????????");
		receptionsService.inserTtestReception(testreception);
		//????????? ???????????? testreceptionid ????????? ?????? testreception??? id??? ????????????
		int testreceptionid = testreception.getTestreceptionid();
		List<String> arr = (ArrayList<String>)json.get("testdataidlist");
		ReceptedTestDataParameter receptedparameter = new ReceptedTestDataParameter();
		receptedparameter.setTestreceptionid(testreceptionid);
		receptedparameter.setTestdataidlist(arr);
		receptedparameter.setPatientid(patientid);
		receptionsService.UpdateTestList(receptedparameter);

		return testreception;
	}
	
	//???????????? ????????????
	@DeleteMapping("/removetestreception")
	public int RemoveTestReception(@RequestBody Map<String,Integer> obj) {
		//?????? testreception ???????????? ???????????? test??? ?????? testreceptionid??? setnull???.
		return receptionsService.RemoveTestReception(obj.get("testreceptionid"));
	}
	//???????????? ????????????
	@DeleteMapping("/removereceptiontreatment")
	public  int RemoveReceptionTreatment(@RequestBody Map<String,Integer> obj) {
		return treatmentsService.deleteTreatment(obj.get("treatmentid"));
	}

	@RequestMapping("/sendRedisMessage")
	public void sendRedisMessage(String topic, String content, HttpServletResponse response) {
		try {
			redisTemplate.convertAndSend(topic, content);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "success");
			pw.println(jsonObject.toString());
			pw.flush();
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//???????????? ????????????
	@PostMapping("/savechatting")
	public void SaveChatting(@RequestBody Map<String,Object>json, HttpServletResponse response) {
		try {
			redisTemplate.setKeySerializer(new StringRedisSerializer());

			redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
			String userid = json.get("userid").toString();
			String chatArrayst =  json.get("chatArray").toString();
			if(chatArrayst.equals("\"\"")) {
				System.out.println("??? ??????????????? ??????????????????");
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			
			List<Map<String,Object>> chatArray = Arrays.asList(mapper.readValue(chatArrayst, Map[].class));
			if(chatArray.size()<1) {
				System.out.println("??? ??????????????? ??????????????????");
				return;
			}
			ValueOperations<String,List<Map<String,Object>>> listOpertions =  redisTemplate.opsForValue();
			//?????? ????????? ???????????? ??????????????? ??????
			redisTemplate.delete(userid);
			//?????? ????????? ???????????? ???????????? ??????
			listOpertions.set(userid, chatArray);
			
			
			//db?????? ??????
			chattingsHistoryService.RemoveChattingHistory(userid);
			
			//db??? ??????
			ChattingHistory chattingHistory = new ChattingHistory();
			chattingHistory.setUserid(userid);
			String jsonStr = mapper.writeValueAsString(chatArray);
			chattingHistory.setHistory(jsonStr);
			chattingsHistoryService.PushChattingHistory(chattingHistory);
			
			
			
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			response.setContentType("application/json; charset=UTF-8");
			pw.println(jsonObject.toString());
			pw.flush();
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//???????????? ????????????
	@GetMapping("/clearchatting/{userid}")
	public void clearChatting(@PathVariable String userid, HttpServletResponse response) {
		try {
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		//?????? ????????? ???????????? ??????????????? ??????
		boolean result = redisTemplate.delete(userid);
		//db?????? ??????
		chattingsHistoryService.RemoveChattingHistory(userid);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("result", result?"Clear success":"Clear fail");
		pw.println(jsonObject.toString());
		pw.flush();
		pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//???????????? ????????????
	@GetMapping("/loadchatting/{userid}")
	public List<Map<String,Object>> LoadChatting(@PathVariable String userid) {
		List<Map<String,Object>> list = null;
		try {
			
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
			ValueOperations<String,List<Map<String,Object>>> valueOpertions =  redisTemplate.opsForValue();
			list = valueOpertions.get(userid);
			
			//?????? ???????????? ?????? ????????? ??????????????? ????????? ?????? ????????? 
			if(list!=null) {
				return list;
			}
			//?????? ????????? DB?????? ?????????
			else {
				ObjectMapper mapper = new ObjectMapper();
				//db?????? ????????????
				ChattingHistory chattinghistory =  chattingsHistoryService.GetChattingHistory(userid);
				if(chattinghistory==null ) {
					System.out.println("db??? ???????????? ???????????? ????????????");
					return list;
				}
				String json = chattinghistory.getHistory();
				list = Arrays.asList(mapper.readValue(json, Map[].class));
				System.out.println("load from db");
				return list;
			}
			
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	//userid??? user????????????
	@GetMapping("/getuser/{userid}")
	public User GetUser(@PathVariable String userid) {
		User user = usersService.getUser(userid);
		return user;
	}
	
	//????????? ???????????? ?????? ????????????
	@GetMapping("/getnetworkstatus")
	public Map<String,Object> GetNetworkStatus() {

		
		String ping = redisTemplate.getConnectionFactory().getConnection().ping();
		Long DBsize = redisTemplate.getConnectionFactory().getConnection().dbSize();
		
		String script = "return redis.pcall('MEMORY', 'USAGE', KEYS[1])";
	    String key = "test";
	    RedisConnection redisConnection = RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
	    Long Rss = (Long)(redisConnection.eval(script.getBytes(StandardCharsets.UTF_8),ReturnType.INTEGER, 1,key.getBytes(StandardCharsets.UTF_8)));
	     RedisConnectionUtils.releaseConnection(redisConnection, redisTemplate.getConnectionFactory());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ping", ping);
		jsonObject.put("DBSize", DBsize);
		jsonObject.put("Rss", Rss);
		
		return jsonObject.toMap();
	}

	
}
