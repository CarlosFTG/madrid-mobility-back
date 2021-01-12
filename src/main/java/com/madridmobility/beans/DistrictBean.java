package com.madridmobility.beans;

public class DistrictBean {
	
	private long id;
	
	private String name;
	
	private String geom;
	
	private long totalBikes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public long getTotalBikes() {
		return totalBikes;
	}

	public void setTotalBikes(long totalBikes) {
		this.totalBikes = totalBikes;
	}
	
	

}
