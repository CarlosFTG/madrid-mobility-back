package com.madridmobility.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.MultiPoint;

@Entity
@Table(name = "streets_bicimad_zone")
public class StreetsNumberEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5573795726045984302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "street_number")
	private String streetNumber;
	
	@Column(name = "coordinates")
	private MultiPoint coordinates;

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

	public MultiPoint getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(MultiPoint coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
