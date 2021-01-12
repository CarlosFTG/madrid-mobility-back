package com.madridmobility.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Point;

@Entity
@Table(name = "bike_accidents")
public class BikeAccidentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "coordinates")
	private Point accidentPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getAccidentPoint() {
		return accidentPoint;
	}

	public void setAccidentPoint(Point accidentPoint) {
		this.accidentPoint = accidentPoint;
	}
	
	
}
