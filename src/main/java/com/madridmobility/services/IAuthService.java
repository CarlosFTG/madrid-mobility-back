package com.madridmobility.services;

import org.json.JSONException;

import com.madridmobility.beans.AuthBean;

public interface IAuthService {

	AuthBean doLogin() throws JSONException;
	
	void registerUser();

}
