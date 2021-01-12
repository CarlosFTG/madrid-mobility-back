package com.madridmobility.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madridmobility.Entities.UserInfoEntity;
import com.madridmobility.dao.IUserInfoDAO;
import com.madridmobility.services.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
	
	@Autowired
	IUserInfoDAO userInfoDAO;

	@Override
	public void registerVisit(String userCity) {
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		Date currentDate = calendar.getTime();
		
		
		
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		
		userInfoEntity.setCity(userCity);
		userInfoEntity.setVisitDate(currentDate);
		
		this.userInfoDAO.save(userInfoEntity);
		
	}

}
