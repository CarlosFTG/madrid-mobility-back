package com.madridmobility.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Polygon;

@Entity
@Table(name = "buffer")
public class BufferEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "coordinates")
	private Polygon coordinates;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Polygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Polygon coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
