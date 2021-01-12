package com.madridmobility.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

@Entity
@Table(name = "quite_streets")
public class QuiteStreetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "tx_capa")
	String streetType;
	
	@Column(name = "tx_nombre")
	String name;
	
	@Column(name = "geom")
	MultiLineString geometry;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultiLineString getGeometry() {
		return geometry;
	}

	public void setGeometry(MultiLineString geometry) {
		this.geometry = geometry;
	}
}
