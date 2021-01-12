package com.madridmobility.services;

import java.util.List;

import org.json.JSONException;

import com.madridmobility.beans.BusStopBean;
import com.vividsolutions.jts.io.ParseException;

public interface IBusService {

	List<BusStopBean> getBusesStopsInfo(String mobilityLabsToken) throws JSONException, ParseException;

	List<BusStopBean> getBusStopsAroundUser(String mobilityLabsToken,float lng, float lat) throws JSONException, ParseException;

}
