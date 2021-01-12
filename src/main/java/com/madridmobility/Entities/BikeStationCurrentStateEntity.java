package com.madridmobility.Entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bike_stations_current_state")
public class BikeStationCurrentStateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "state_date")
	Date stateDate;

	@Column(name = "station_id")
	String stationId;

	@Column(name = "free_docks")
	Long freeDocks;

	@Column(name = "available_bikes")
	Long availableBikes;

	@Column(name = "reservations")
	Long reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Long getFreeDocks() {
		return freeDocks;
	}

	public void setFreeDocks(Long freeDocks) {
		this.freeDocks = freeDocks;
	}

	public Long getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(Long availableBikes) {
		this.availableBikes = availableBikes;
	}

	public Long getReservations() {
		return reservations;
	}

	public void setReservations(Long reservations) {
		this.reservations = reservations;
	}

}
