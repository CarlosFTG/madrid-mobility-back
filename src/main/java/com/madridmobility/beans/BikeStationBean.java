package com.madridmobility.beans;

import java.math.BigDecimal;
import java.util.Map;

import org.json.JSONObject;

public class BikeStationBean {
	
	private Long id;
	
	String stationId;
	
	String address;
	
	String name;
	
	Long availableBikes;
	
	Long freeDocks;
	
	Long reservations;
	
	Double distance;
	
	Map<String, String> pointsList;

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getPointsList() {
		return pointsList;
	}

	public void setPointsList(Map<String, String> pointsList) {
		this.pointsList = pointsList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(Long dockBikes) {
		this.availableBikes = dockBikes;
	}

	public Long getFreeDocks() {
		return freeDocks;
	}

	public void setFreeDocks(Long freeBases) {
		this.freeDocks = freeBases;
	}

	public Long getReservations() {
		return reservations;
	}

	public void setReservations(Long reservedBikes) {
		this.reservations = reservedBikes;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	

}
