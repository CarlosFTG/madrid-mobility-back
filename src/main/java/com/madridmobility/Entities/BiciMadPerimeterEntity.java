package com.madridmobility.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

@Entity
@Table(name = "bicimad_perimeter")
public class BiciMadPerimeterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "geom")
	private Polygon coordinates;

	public Polygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Polygon coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
