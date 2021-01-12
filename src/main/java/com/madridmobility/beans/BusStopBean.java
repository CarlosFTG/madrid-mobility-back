package com.madridmobility.beans;

import java.util.HashMap;
import java.util.List;

public class BusStopBean {
	
	private int busStopId;
	
	private HashMap<String, String> coordinates;
	
	private String name;
	
	private List<ArrivalBean> arrivalBeanList;
	
	private List<BusLineBean> busLineBeanList;

	public HashMap<String, String> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(HashMap<String, String> coordsHashMap) {
		this.coordinates = coordsHashMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBusStopId() {
		return busStopId;
	}

	public void setBusStopId(int busStopId) {
		this.busStopId = busStopId;
	}

	public List<BusLineBean> getBusLineBeanList() {
		return busLineBeanList;
	}

	public void setBusLineBeanList(List<BusLineBean> busLineBeanList) {
		this.busLineBeanList = busLineBeanList;
	}

	public List<ArrivalBean> getArrivalBeanList() {
		return arrivalBeanList;
	}

	public void setArrivalBeanList(List<ArrivalBean> arrivalBeanList) {
		this.arrivalBeanList = arrivalBeanList;
	}

}
