package com.madridmobility.beans;

import com.vividsolutions.jts.geom.Point;

public class BikeAccidentBean {
	private Long id;

	private String accidentPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccidentPoint() {
		return accidentPoint;
	}

	public void setAccidentPoint(String accidentPoint) {
		this.accidentPoint = accidentPoint;
	}
}
