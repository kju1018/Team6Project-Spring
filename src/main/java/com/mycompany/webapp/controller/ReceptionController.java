package com.mycompany.webapp.controller;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	//전체 예약정보 가져오기
	@GetMapping("/reservationlist")
	public List<Reservation> ReservationList() {
		List<Reservation> list= reservationservice.getReservationList();	
		return list;
	}
	//예약정보 등록하기
	@PostMapping("/registerreservation")
	public Reservation RegisterReservation(@RequestBody List<Object> obj) {
		
		//추가된 예약 데이터
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
	//예약정보 삭제하기
	@DeleteMapping("/removereservation")
	public int RemoveReservation(@RequestBody Map<String,Integer> obj) {
		return reservationservice.RemoveReservation(obj.get("reservationid"));
	}
	//예약정보 수정하기
	@PutMapping("/updatereservation")
	public int UpdateReservation(@RequestBody Reservation reservation) {
		reservationservice.UpdateReservation(reservation);
		return reservation.getReservationid();
	}
	
	
	//전체 환자정보 가져오기
	@GetMapping("/patientlist")
	public List<Patient> PatientList() {
		List<Patient> list= patientservice.getPatientList();	
		return list;
	}
	//특정 환자 가져오기
	@GetMapping("/getpatient")
	public Patient getPatient(int patientid) {
		return	patientservice.getPatient(patientid);	
		 
	}
	//환자등록하기
	@PostMapping("/registerpatient")
	public int RegisterPatient(@RequestBody Patient patient) {
		patientservice.RegisterPatient(patient);
		return patient.getPatientid();
	}
	//환자수정하기
	@PutMapping("/updatepatient")
	public int UpdatePatient(@RequestBody Patient patient) {
		patientservice.UpdatePatient(patient);
		return patient.getPatientid();
	}
	
	
	//전체 진료정보 불러오기 + 의료진 정보도 추가
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
	
	//해당 진료의 상세정보 불러오기
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
	
	//해당 환자가 처방받았지만 아직 접수되지 않은 검사데이터 불러오기
	@GetMapping("/prescriptiontest")
	public Map<String,Object> PrescriptionTest(int patientid) {
	
		List<String> PrescriptionTestDataIdList = receptionsService.GetPrescriptionTestDataByPatientid(patientid);
		List<TestData> PrescriptionTestDataList = new ArrayList<TestData>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 처방된 테스트가 없을때 예외처리
		if(PrescriptionTestDataIdList.size()<1) {return map;}
		
		
		for(int i=0; i<PrescriptionTestDataIdList.size(); i++) {
			TestData testdata = receptionsService.GetTestData(PrescriptionTestDataIdList.get(i));
			PrescriptionTestDataList.add(testdata);
		}
		//받은 테스트 데이터들을 그룹코드로 정렬함
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
	
	//진료 접수하기
	@PostMapping("/receptiontreatment")
	public Treatment ReceptionTreatment(@RequestBody Treatment treatment) {
		System.out.println(treatment);
		Treatment newtreatment =treatmentsService.create(treatment);
		return newtreatment ;
	}
	//오늘날짜의 진료데이터가져오기
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
	//오늘 날짜의 접수된 검사데이터 가져오기
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
	//검사 접수하기
	@PostMapping("/receptiontest")
	public TestReception ReceptionTest(@RequestBody Map<String,Object>json) {
		int patientid = Integer.parseInt(json.get("patientid").toString());
		System.out.println(json);
		//TestReception만들기
		TestReception testreception = new TestReception();
		testreception.setTestdate(new Date());
		testreception.setPatientid(patientid);
		testreception.setStatus("대기중");
		testreception.setResultstatus("미입력");
		receptionsService.inserTtestReception(testreception);
		//선택된 검사들의 testreceptionid 칼럼에 만든 testreception의 id값 넣어주기
		int testreceptionid = testreception.getTestreceptionid();
		List<String> arr = (ArrayList<String>)json.get("testdataidlist");
		System.out.println(json.get("testdataidlist"));
		ReceptedTestDataParameter receptedparameter = new ReceptedTestDataParameter();
		receptedparameter.setTestreceptionid(testreceptionid);
		receptedparameter.setTestdataidlist(arr);
		receptedparameter.setPatientid(patientid);
		receptionsService.UpdateTestList(receptedparameter);

		return testreception;
	}
	
	//검사접수 삭제하기
	@DeleteMapping("/removetestreception")
	public int RemoveTestReception(@RequestBody Map<String,Integer> obj) {
		//해당 testreception 삭제하면 자동으로 test에 있는 testreceptionid값 setnull됨.
		return receptionsService.RemoveTestReception(obj.get("testreceptionid"));
	}
	//진료접수 삭제하기
	@DeleteMapping("/removereceptiontreatment")
	public  int RemoveReceptionTreatment(@RequestBody Map<String,Integer> obj) {
		return treatmentsService.deleteTreatment(obj.get("treatmentid"));
	}
	//테스트
	@GetMapping("/test")
	public List<Object> test(int treatmentid, int patientid) {
		List<String> tlist = receptionsService.GetPrescriptionTestDataByTreatmentid(treatmentid);
		List<String> plist = receptionsService.GetPrescriptionTestDataByPatientid(patientid);
		List<Object> list = new ArrayList<Object>();
		list.add(tlist);
		list.add(plist);
		return list;
	}
	
	
	@RequestMapping("/sendRedisMessage")
	public void sendRedisMessage(String topic, String content, HttpServletResponse response) {
		try {			
			System.out.println(topic + " " + content);
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
	
	//채팅정보 저장하기
	@PostMapping("/savechatting")
	public void SaveChatting(@RequestBody List<Map<String,Object>> list, HttpServletResponse response) {
		try {
			redisTemplate.setKeySerializer(new StringRedisSerializer());

			redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
			ValueOperations<String,List<Map<String,Object>>> listOpertions =  redisTemplate.opsForValue();
			redisTemplate.delete("chatArray");
			listOpertions.set("chatArray", list);
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
	//채팅정보 불러오기
	@GetMapping("/loadchatting")
	public List<Map<String,Object>> LoadChatting() {
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		ValueOperations<String,List<Map<String,Object>>> valueOpertions =  redisTemplate.opsForValue();
		List<Map<String,Object>> list = valueOpertions.get("chatArray");
		if(list==null) {
			list= new ArrayList<Map<String,Object>>();
		}
		return list;
		
		
	}
	//userid로 user불러오기
	@GetMapping("/getuser/{userid}")
	public User GetUser(@PathVariable String userid) {
		User user = usersService.getUser(userid);
		return user;
	}
	
	//서버의 네트워크 상태 가져오기
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
	
	
	@GetMapping("/test1")
	public void test() {
		patientservice.UpdateLastTreatment(10);
		
	}
	
}
