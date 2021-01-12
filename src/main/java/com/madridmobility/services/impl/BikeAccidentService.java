package com.madridmobility.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madridmobility.Entities.BikeAccidentEntity;
import com.madridmobility.beans.BikeAccidentBean;
import com.madridmobility.dao.IBikeAccidentDAO;
import com.madridmobility.services.IBikeAccidentService;

@Service
public class BikeAccidentService implements IBikeAccidentService {
	
	@Autowired
	IBikeAccidentDAO bikeAccidentDAO;

	@Override
	public List<BikeAccidentBean> getBikeAccidentes() {
		
		List<BikeAccidentBean> bikeAccidentsBeanList = new ArrayList<BikeAccidentBean>();
		
		List<BikeAccidentEntity> bikeAccidentsList = this.bikeAccidentDAO.findAll();
		
		for(BikeAccidentEntity bikeAccident:bikeAccidentsList) {
			
			BikeAccidentBean bikeAccidentsBean = new BikeAccidentBean();
			bikeAccidentsBean.setAccidentPoint(bikeAccident.getAccidentPoint().toString());
			bikeAccidentsBeanList.add(bikeAccidentsBean);
		}
		return bikeAccidentsBeanList;
	}

}
