package com.madridmobility.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import com.madridmobility.Entities.BiciMadPerimeterEntity;
import com.madridmobility.beans.AuthBean;
import com.madridmobility.beans.BiciMadPerimeterBean;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.FindNearStationsBean;
import com.vividsolutions.jts.io.ParseException;


public interface IBikeStationStationService {

	List<BikeStationBean> getBikeStationsInfo(String mobilityLabsToken) throws  JSONException, ParseException;

	BiciMadPerimeterBean getBiciMadPerimetre();
	
	List<BikeStationBean> saveBikeStations(List<BikeStationBean> bikeStationList) throws JSONException, ParseException;

	List<BikeStationBean> getClosestStation(FindNearStationsBean findNearStationsBean) throws ParseException, JSONException;


}
