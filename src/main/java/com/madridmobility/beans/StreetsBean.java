package com.madridmobility.beans;

import java.util.List;

public class StreetsBean {

	String name;
	
	String geom;
	
	List<PortalsBean> portals;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PortalsBean> getPortals() {
		return portals;
	}

	public void setPortals(List<PortalsBean> portals) {
		this.portals = portals;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}	
	
	
}
