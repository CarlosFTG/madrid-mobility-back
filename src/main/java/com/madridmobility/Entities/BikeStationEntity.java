package com.madridmobility.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Point;
@Entity
@Table(name = "bike_stations")
public class BikeStationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "station_id")
	String stationId;
	
	@Column(name = "address")
	String address;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "coordinates")
	private Point coordinates;
	
	@Column(name = "free_docks")
	Long freeDocks;

	@Column(name = "available_bikes")
	Long availableBikes;

	@Column(name = "reservations")
	Long reservations;
	
	@Column(name = "date")
	Date date;
	
	Double distance;

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

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}


