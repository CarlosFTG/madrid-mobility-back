package com.madridmobility.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madridmobility.Entities.BiciMadPerimeterEntity;
import com.madridmobility.Entities.BikeAccidentEntity;
import com.madridmobility.beans.AuthBean;
import com.madridmobility.beans.BiciMadPerimeterBean;
import com.madridmobility.beans.BikeAccidentBean;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.BusStopBean;
import com.madridmobility.beans.DistrictBean;
import com.madridmobility.beans.FindNearStationsBean;
import com.madridmobility.beans.LoginUserBean;
import com.madridmobility.beans.QuiteStreetBean;
import com.madridmobility.beans.StreetsBean;
import com.madridmobility.beans.StreetsNumberBean;
import com.madridmobility.services.IAuthService;
import com.madridmobility.services.IBikeAccidentService;
import com.madridmobility.services.IBikeStationStationService;
import com.madridmobility.services.IBusService;
import com.madridmobility.services.IQuiteStreetService;
import com.madridmobility.services.IStreetsService;
import com.madridmobility.services.IUserInfoService;
import com.vividsolutions.jts.io.ParseException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AppRestController {
	
	@Autowired
	IBikeStationStationService bikeStationService;
	
	@Autowired
	IStreetsService streetsService;
	
	@Autowired
	IUserInfoService userInfoService;
	
	@Autowired
	IBikeAccidentService bikeAccidentService;
	
	@Autowired
	IAuthService authService;
	
	@Autowired
	IQuiteStreetService quiteStreetService;
	
	
	
	ObjectMapper mapper = new ObjectMapper();
	
	String mobilityLabsToken = null;
	
	@GetMapping("/EMTServices/login")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<AuthBean> doLogin() throws  JSONException, ParseException  {
		

		return ResponseEntity.ok( this.authService.doLogin());
	}
	
	@GetMapping("/EMTServices/registerUser")
	@ResponseStatus(HttpStatus.FOUND)
	public void registerUser(String email, String password) throws  JSONException, ParseException  {
		
		this.authService.registerUser();
		
	}
	
	@GetMapping("/EMTServices/loginUser")
	@ResponseStatus(HttpStatus.FOUND)
	public AuthBean doLoginUser(String email, String password) throws  JSONException, ParseException  {
		
		System.out.println(email);
		System.out.println(password);
		
		return null;
		
	}
	
	@GetMapping("/EMTServices/getBiciMadPerimeter")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<BiciMadPerimeterBean> getBiciMadPerimetre() throws  JSONException, ParseException  {
		return ResponseEntity.ok(this.bikeStationService.getBiciMadPerimetre());
	}
	
	@GetMapping("/EMTServices/checkAvaibility")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<BikeStationBean>> getStationsInfo(@RequestParam String emtToken) throws  JSONException, ParseException  {

		return ResponseEntity.ok(this.bikeStationService.getBikeStationsInfo(emtToken));
		
	}
	
	
	
	@GetMapping("/EMTServices/getStreets")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<StreetsNumberBean>> getStreets() throws  JSONException, ParseException  {

		return ResponseEntity.ok(this.streetsService.getStreets());
		
	}
	
	@GetMapping("/EMTServices/getStreetsTopo")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<StreetsBean>> getStreetTopo() throws  JSONException, ParseException  {

		return ResponseEntity.ok(this.streetsService.getStreetsTopo());
		
	}

	
	@PostMapping("/EMTServices/findClosestStations")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<BikeStationBean>> getClosestStation(@RequestBody FindNearStationsBean findNearStationsBean) throws  JSONException, ParseException  {

		return ResponseEntity.ok(this.bikeStationService.getClosestStation(findNearStationsBean));
	}
	
	@PostMapping("/EMTServices/registerVisit")
	@ResponseStatus(HttpStatus.FOUND)
	public void registerVisit(@RequestBody String userCity) throws  JSONException, ParseException  {

		this.userInfoService.registerVisit(userCity);
	}
	
	@GetMapping("/EMTServices/getDistricts")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<DistrictBean>> getDistricts() throws  JSONException, ParseException, SQLException  {

		return ResponseEntity.ok(this.streetsService.getDistricts());
		
	}
	
	@GetMapping("/EMTServices/getBikeAccidents")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<BikeAccidentBean>> getBikeAccidents() throws  JSONException, ParseException, SQLException  {

		return ResponseEntity.ok(this.bikeAccidentService.getBikeAccidentes());
		
	}
	
	@GetMapping("/EMTServices/getQuiteStreets")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<QuiteStreetBean>> getQuiteStreets() throws  JSONException, ParseException, SQLException  {

		return ResponseEntity.ok(this.quiteStreetService.getQuiteStreets());
		
	}
	
//	@GetMapping("/EMTServices/deleteBikeStationRegisters")
//	@ResponseStatus(HttpStatus.FOUND)
//	public void deleteBikeStationRegisters() throws  JSONException, ParseException  {
//		this.bikeStationService.deleteBikeStationRegisters();
//	}
//	
	
}