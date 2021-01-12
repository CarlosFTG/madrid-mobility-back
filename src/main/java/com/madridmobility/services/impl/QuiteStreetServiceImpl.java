package com.madridmobility.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madridmobility.Entities.QuiteStreetEntity;
import com.madridmobility.beans.QuiteStreetBean;
import com.madridmobility.dao.IQuiteStreetDAO;
import com.madridmobility.services.IQuiteStreetService;

@Service
public class QuiteStreetServiceImpl implements IQuiteStreetService{
	
	@Autowired
	IQuiteStreetDAO quiteStreetDAO;

	@Override
	public List<QuiteStreetBean> getQuiteStreets() {
		
		List<QuiteStreetBean> quiteStreetBeanList = new ArrayList<QuiteStreetBean>();
		
		List<QuiteStreetEntity> quiteStreetEntityList = this.quiteStreetDAO.findAll();
		
		for(QuiteStreetEntity quiteStreetEntity:quiteStreetEntityList) {
			
			QuiteStreetBean quiteStreetBean = new QuiteStreetBean();
			
			quiteStreetBean.setName(quiteStreetEntity.getName());
			quiteStreetBean.setTypeOfStreet(quiteStreetEntity.getStreetType());
			quiteStreetBean.setLine(quiteStreetEntity.getGeometry().toString());
			quiteStreetBeanList.add(quiteStreetBean);
		}
		
		return quiteStreetBeanList;
	}

}
