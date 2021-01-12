package com.madridmobility.dao;

import java.util.List;

import com.madridmobility.Entities.BikeStationEntity;
import com.madridmobility.Entities.BufferEntity;
import com.madridmobility.Entities.UserLocationEntity;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.FindNearStationsBean;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;

public interface IBikeStationDAO {

	/**
	 * Method to know bike stations store in the DB
	 * @return
	 */
	Long countBikeStationInDB();

	BufferEntity createBuffer(Point point, Long distance);

	List<BikeStationEntity> getNearStations(BufferEntity buffer) throws ParseException;

	List<BikeStationEntity> getClosestStation(UserLocationEntity userLocationEntity, FindNearStationsBean findNearStationsBean);

	void updateBikeStation(BikeStationBean bikeStation);


}
