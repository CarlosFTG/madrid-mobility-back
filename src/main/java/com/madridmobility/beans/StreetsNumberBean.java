package com.madridmobility.beans;

public class StreetsNumberBean {
private int id;
	
	private String streetName;
	
	private String streetNumber;
	
	private String coordinates;
	
	private String wholeAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getWholeAddress() {
		return wholeAddress;
	}

	public void setWholeAddress(String wholeAddress) {
		this.wholeAddress = wholeAddress;
	}
	
	
}
