package com.madridmobility.services.impl;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.madridmobility.beans.AuthBean;
import com.madridmobility.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Override
	public AuthBean doLogin() throws JSONException {
		
		String token = null;

		final String checkAvaibilityUri = "https://openapi.emtmadrid.es/v1/hello/";

		RestTemplate restTemplate = new RestTemplate();
		String avaibilityResult = restTemplate.getForObject(checkAvaibilityUri, String.class);

		JSONObject jObj = new JSONObject(avaibilityResult.toString());

		@SuppressWarnings("unused")
		String petitionCode = (String) jObj.get("code");


		if (petitionCode.equals("00")) {
			final String loginUri = "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/";

			HttpHeaders loginHeaders = new HttpHeaders();

			loginHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			loginHeaders.setContentType(MediaType.APPLICATION_JSON);

			loginHeaders.set("email", "cftejedag@gmail.com");

			loginHeaders.set("password", "AranjueZ86!!");

			HttpEntity<String> entityReq = new HttpEntity<String>("parameters", loginHeaders);

			RestTemplate loginRestTemplate = new RestTemplate();
			ResponseEntity<String> loginResultString = loginRestTemplate.exchange(loginUri, HttpMethod.GET, entityReq,
					String.class);

			String loginResult = loginResultString.toString().substring(5, loginResultString.toString().length());

			JSONObject jObjLoginResponse = new JSONObject(loginResult);

			String stringResponse = jObjLoginResponse.get("data").toString();

			String accessTokenString = stringResponse.substring(1, stringResponse.length() - 1);

			JSONObject jObjLoginResponseExtracted = new JSONObject(accessTokenString);

			token = jObjLoginResponseExtracted.get("accessToken").toString();
			
		}
		
		AuthBean  authBean = new AuthBean();
		authBean.setToken(token);
		return authBean;
	}

	@Override
	public void registerUser() {
		// TODO Auto-generated method stub
		
	}
	
	
}
