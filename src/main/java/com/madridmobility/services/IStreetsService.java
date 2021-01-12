package com.madridmobility.services;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;

import com.madridmobility.beans.DistrictBean;
import com.madridmobility.beans.StreetsBean;
import com.madridmobility.beans.StreetsNumberBean;
import com.vividsolutions.jts.io.ParseException;

public interface IStreetsService {
	List<StreetsNumberBean> getStreets() throws  JSONException, ParseException;

	List<DistrictBean> getDistricts() throws SQLException;

	List<StreetsBean> getStreetsTopo();
}
