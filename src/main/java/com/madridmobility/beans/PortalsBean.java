package com.madridmobility.beans;

import javax.persistence.Column;

import com.vividsolutions.jts.geom.Point;

public class PortalsBean {
private int id;
	
	private String coordinates;
	
	private String cdMuni;
	
	private String numeCvia;
	
	private String etinum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getCdMuni() {
		return cdMuni;
	}

	public void setCdMuni(String cdMuni) {
		this.cdMuni = cdMuni;
	}

	public String getNumeCvia() {
		return numeCvia;
	}

	public void setNumeCvia(String numeCvia) {
		this.numeCvia = numeCvia;
	}

	public String getEtinum() {
		return etinum;
	}

	public void setEtinum(String etinum) {
		this.etinum = etinum;
	}
	
	
}
