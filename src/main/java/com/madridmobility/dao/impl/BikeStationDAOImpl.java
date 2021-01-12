package com.madridmobility.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madridmobility.Entities.BikeStationEntity;
import com.madridmobility.Entities.BufferEntity;
import com.madridmobility.Entities.UserLocationEntity;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.DistanceResult;
import com.madridmobility.beans.FindNearStationsBean;
import com.madridmobility.dao.IBikeStationDAO;
import com.madridmobility.dao.IBufferDAO;
import com.madridmobility.dao.IUserLocationDAO;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;

@Component("BikeStationDAOImpl")
public class BikeStationDAOImpl implements IBikeStationDAO {
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	IUserLocationDAO userLocationDAO;

	@Autowired
	IBufferDAO bufferDAO;

	@Override
	public Long countBikeStationInDB() {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT (*) FROM BikeStationEntity ");

		Query q = em.createQuery(sb.toString());

		Long stationsInDB = (Long) q.getSingleResult();

		return stationsInDB;

	}

	@Override
	public BufferEntity createBuffer(Point point, Long distance) {

		StringBuffer sb = new StringBuffer();

		BufferEntity buffer = new BufferEntity();

		sb.append("select buffer(p.coordinates, (:distance)) from UserLocationEntity p " + "where id in (:point)");

		Query q = em.createQuery(sb.toString());
		q.setParameter("point", new Long(1));
		q.setParameter("distance", Double.parseDouble(distance.toString()));
		Polygon bufferCreated = (Polygon) q.getSingleResult();

		bufferCreated.setSRID(3857);

		buffer.setCoordinates(bufferCreated);

		// this.bufferDAO.save(buffer);

		this.userLocationDAO.deleteById(new Long(1));

		return buffer;

	}

	@Override
	public List<BikeStationEntity> getNearStations(BufferEntity buffer) throws ParseException {

		List<BikeStationEntity> bikeStationsFounds = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("select a from BikeStationEntity a " + "where within(a.coordinates, :buffer ) = true");

		Query q = em.createQuery(sb.toString());
		// q.setParameter("perimeter", biciMadPerimeterEntityFound );
		q.setParameter("buffer", buffer.getCoordinates());

		try {
			bikeStationsFounds = (List<BikeStationEntity>) q.getResultList();
		} catch (NoResultException nre) {
		}

		this.bufferDAO.delete(buffer);

		return bikeStationsFounds;
	}

	@Override
	public List<BikeStationEntity> getClosestStation(UserLocationEntity userlocation,
			FindNearStationsBean findNearStationsBean) {

		int numberOfResults = findNearStationsBean.getNumberOfResults();

		List<BikeStationEntity> bikeStationEntityList = new ArrayList<>();

		List<BikeStationEntity> bikeStationList = new ArrayList<>();
		
		List<Double>distanceResultList=new ArrayList<>();
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb.append("select a from BikeStationEntity a " + "order by distance (a.coordinates, :userLocation) ASC");
		sb2.append("select  distance(a.coordinates,:userLocation)  from BikeStationEntity a " + "order by distance (a.coordinates, :userLocation) ASC");


		Query q = em.createQuery(sb.toString());
		Query q2 = em.createQuery(sb2.toString());
		q.setParameter("userLocation", userlocation.getCoordinates());
		q2.setParameter("userLocation", userlocation.getCoordinates());
		try {
			bikeStationList = (List<BikeStationEntity>) q.getResultList();
			distanceResultList=q2.getResultList();
			
		} catch (NoResultException nre) {
		}
		int counter = 0;
		
		for(Object distanceResult:distanceResultList) {
			System.out.println(distanceResult);
		}
		for (BikeStationEntity bikeStation : bikeStationList) {

			if (counter < numberOfResults) {
				bikeStation.setDistance(distanceResultList.get(counter));
				bikeStationEntityList.add(bikeStation);
			}

			counter++;
		}
		List<BikeStationEntity>bikeStationEntityWithDistance=new ArrayList<>();

			return bikeStationEntityList;
		
	}

	@Override
	@Transactional
	public void updateBikeStation(BikeStationBean bikeStation) {
		StringBuffer sb = new StringBuffer();
		sb.append("update BikeStationEntity set availableBikes = :availableBikes, freeDocks = :freeDocks, reservations = :reservations"
				+ " WHERE stationId = :stationId ");
		
		Query q = em.createQuery(sb.toString());
		
		q.setParameter("availableBikes", bikeStation.getAvailableBikes());
		q.setParameter("freeDocks", bikeStation.getFreeDocks());
		q.setParameter("reservations", bikeStation.getReservations());
		q.setParameter("stationId", bikeStation.getStationId());
		q.executeUpdate();
	}

}
