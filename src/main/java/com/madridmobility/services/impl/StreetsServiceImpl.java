package com.madridmobility.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madridmobility.Entities.DistrictEntity;
import com.madridmobility.Entities.StreetsEntity;
import com.madridmobility.Entities.StreetsNumberEntity;
import com.madridmobility.beans.DistrictBean;
import com.madridmobility.beans.StreetsBean;
import com.madridmobility.beans.StreetsNumberBean;
import com.madridmobility.converter.ConverterUtil;
import com.madridmobility.dao.IDistrictDAO;
import com.madridmobility.dao.IDistrictGenericDAO;
import com.madridmobility.dao.IStreetsGenericDAO;
import com.madridmobility.dao.IStreetsNumberEntityGenericDAO;
import com.madridmobility.services.IStreetsService;
import com.vividsolutions.jts.io.ParseException;

@Service
public class StreetsServiceImpl implements IStreetsService {

	final static Logger logger = Logger.getLogger(StreetsServiceImpl.class);

	@Autowired
	IStreetsNumberEntityGenericDAO streetsNumberEntityGenericDAO;

	@Autowired
	IStreetsGenericDAO streetsEntityGenericDAO;

	@Autowired
	IDistrictGenericDAO districtGenericDAO;

	@Autowired
	IDistrictDAO districtDAO;

	/**
	 * Method used to pass info from db in local to db in heroku Before activate,
	 * change properties to local connection
	 */
	// @Autowired
	// IStreetsNumberDAOImpl streetsNumberEntityDAO;

	@Override
	public List<StreetsNumberBean> getStreets() throws JSONException, ParseException {

		Iterable<StreetsNumberEntity> streetsNumberEntityList = this.streetsNumberEntityGenericDAO.findAll();

		/**
		 * Method used to pass info from db in local to db in heroku Before activate,
		 * change properties to local connection
		 */
		// List<StreetsNumberEntity>streetsNumberEntityList=streetsNumberEntityDAO.findAll();

		List<StreetsNumberBean> streetsbeanList = ConverterUtil.transformFromStreetsNumberEntityList(streetsNumberEntityList);

		// streetsNumberEntityGenericDAO.saveAll(streetsEntityList);

		return streetsbeanList;
	}

	@Override
	public List<StreetsBean> getStreetsTopo() {
		Iterable<StreetsEntity> streetsEntityList = this.streetsEntityGenericDAO.findAll();
		List<StreetsBean> streetsbeanList = ConverterUtil.transformFromStreetsEntityList(streetsEntityList);
		return streetsbeanList;
	}

	@Override
	public List<DistrictBean> getDistricts() throws SQLException {

		List<DistrictBean> districtBeanList = new ArrayList<DistrictBean>();
		List<DistrictEntity> districtEntityList = (List<DistrictEntity>) this.districtGenericDAO.findAll();
		
		List<DistrictBean> districtsBeanListWithTotal = this.districtDAO.getDistrictsWithTotalBikes();
		
		

		for ( DistrictEntity districtEntity : districtEntityList) {

			DistrictBean districtBean = new DistrictBean();

			districtBean.setName(districtEntity.getName());
			districtBean.setGeom(districtEntity.getGeom().toString());
			districtBean.setTotalBikes(findInList(districtBean.getName(),districtsBeanListWithTotal));
			
			districtBeanList.add(districtBean);
		}
		
		

		return districtBeanList;
	}

	private Long findInList(String districtName, List<DistrictBean>districtsBeanListWithTotal) {
		
		Long totalBikes = new Long(0);
		
		for (  DistrictBean districtEntity : districtsBeanListWithTotal) {
			if(districtEntity.getName().equals(districtName)) {
				totalBikes = districtEntity.getTotalBikes();
			}
		}
		
		return totalBikes ;
	}

}
