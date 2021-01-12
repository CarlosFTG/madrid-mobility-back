package com.madridmobility.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madridmobility.Entities.BikeStationEntity;
import com.madridmobility.Entities.PortalsEntity;
import com.madridmobility.Entities.StreetsEntity;
import com.madridmobility.Entities.StreetsNumberEntity;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.PortalsBean;
import com.madridmobility.beans.StreetsBean;
import com.madridmobility.beans.StreetsNumberBean;
import com.madridmobility.dao.IStreetsGenericDAO;
import com.madridmobility.dao.IStreetsNumberEntityGenericDAO;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 * Class to transform from entity to bean
 * 
 * @author cftej
 *
 */
@Service
public class ConverterUtil {

	@Autowired
	IStreetsNumberEntityGenericDAO streetsNumberEntityGenericDAO;

	public static List<BikeStationBean> transformFromBikeStationEntityList(
			List<BikeStationEntity> bikeStationEntityList) throws JSONException {

		List<BikeStationBean> bikeStationBeanList = new ArrayList<BikeStationBean>();

		for (BikeStationEntity bikeStationEntity : bikeStationEntityList) {
			BikeStationBean bikeStationBean = new BikeStationBean();
			bikeStationBean = transformFromBikeStationEntity(bikeStationEntity);
			bikeStationBeanList.add(bikeStationBean);
		}

		return bikeStationBeanList;
	}

	public static List<BikeStationEntity> transformFromBikeStationBeanList(List<BikeStationBean> bikeStationBeanList)
			throws JSONException, ParseException {

		List<BikeStationEntity> bikeStationEntityList = new ArrayList<BikeStationEntity>();

		for (BikeStationBean bikeStationBean : bikeStationBeanList) {

			BikeStationEntity bikeStationEntity = transformFromBikeStationBean(bikeStationBean);

			bikeStationEntity.getCoordinates().setSRID(3857);

			bikeStationEntityList.add(bikeStationEntity);
		}

		return bikeStationEntityList;
	}

	public static BikeStationBean transformFromBikeStationEntity(BikeStationEntity bikeStationEntity)
			throws JSONException {

		BikeStationBean bikeStationBean = new BikeStationBean();

		bikeStationBean.setId((long) bikeStationEntity.getId());

		bikeStationBean.setStationId(bikeStationEntity.getStationId());

		bikeStationBean.setAddress(bikeStationEntity.getAddress());

		bikeStationBean.setName(bikeStationEntity.getName());

		if (bikeStationEntity.getDistance() != null) {
			bikeStationBean.setDistance(transformTometer(bikeStationEntity.getDistance()) );
		}

		// bikeStationBean.setDistance(bikeStationEntity.getDistance());

		bikeStationBean.setAvailableBikes(bikeStationEntity.getAvailableBikes());

		bikeStationBean.setFreeDocks(bikeStationEntity.getFreeDocks());

		bikeStationBean.setReservations(bikeStationEntity.getReservations());

		JSONObject geoJson = new JSONObject();

		// bikeStationBean.setPointsList(bikeStationEntity.getCoordinates());

		JSONObject geoJsonGeom = new JSONObject();

		geoJsonGeom.put("coordinates", bikeStationEntity.getCoordinates().toString());

		geoJson.put("geometry", geoJsonGeom);

		// bikeStationBean.setPointsList(geoJson);

		HashMap<String, String> coordsHashMap = new HashMap<>();

		int indexOfFirstParenthesis = bikeStationEntity.getCoordinates().toString().indexOf('(');

		int indexOfLastParenthesis = bikeStationEntity.getCoordinates().toString().indexOf(')');

		String coords = bikeStationEntity.getCoordinates().toString().substring(indexOfFirstParenthesis + 1,
				indexOfLastParenthesis);

		coordsHashMap.put("coordinates", coords);

		bikeStationBean.setPointsList(coordsHashMap);

		return bikeStationBean;
	}

	public static BikeStationEntity transformFromBikeStationBean(BikeStationBean bikeStationBean)
			throws JSONException, ParseException {

		String coordinatesToString = null;

		String subStringCoordinates = null;

		String subStringCoordinates1 = null;

		BikeStationEntity bikeStationEntity = new BikeStationEntity();

		bikeStationEntity.setId(bikeStationBean.getId());

		bikeStationEntity.setStationId(bikeStationBean.getStationId());

		bikeStationEntity.setAddress(bikeStationBean.getAddress());

		bikeStationEntity.setName(bikeStationBean.getName());

		bikeStationEntity.setAvailableBikes(bikeStationBean.getAvailableBikes());

		bikeStationEntity.setFreeDocks(bikeStationBean.getFreeDocks());
		
		Date date= new Date();
		
		bikeStationEntity.setDate(date);

		bikeStationEntity.setReservations(bikeStationBean.getReservations());

		subStringCoordinates = bikeStationBean.getPointsList().toString().substring(13,
				bikeStationBean.getPointsList().toString().indexOf(","));

		subStringCoordinates1 = bikeStationBean.getPointsList().toString().substring(
				bikeStationBean.getPointsList().toString().indexOf(",") + 1,
				bikeStationBean.getPointsList().toString().length() - 1);

		coordinatesToString = "POINT(" + subStringCoordinates + " " + subStringCoordinates1 + ")";

		bikeStationEntity.setCoordinates(wktToGeometry(coordinatesToString));

		return bikeStationEntity;
	}

	public static List<StreetsNumberBean> transformFromStreetsNumberEntityList(
			Iterable<StreetsNumberEntity> streetsEntityList) throws JSONException {
		List<StreetsNumberBean> streetsBeanList = new ArrayList<>();

		for (StreetsNumberEntity streetsEntity : streetsEntityList) {
			StreetsNumberBean streetsBean = transformFromStreetsNumberEntity(streetsEntity);
			streetsBeanList.add(streetsBean);
		}
		return streetsBeanList;
	}

	public static StreetsNumberBean transformFromStreetsNumberEntity(StreetsNumberEntity streetsEntity) throws JSONException {

		StreetsNumberBean streetsNumberBean = new StreetsNumberBean();
		
		//streetsNumberBean.setCoordinates(streetsEntity.getCoordinates().toString());
		streetsNumberBean.setStreetNumber(streetsEntity.getStreetNumber());
		streetsNumberBean.setStreetName(streetsEntity.getStreetName());
		streetsNumberBean.setWholeAddress(streetsEntity.getStreetName()+" "+streetsEntity.getStreetNumber());
		
		return streetsNumberBean;
	}



	public static Point wktToGeometry(String wellKnownText) throws ParseException {

		Point point = (Point) new WKTReader().read(wellKnownText);
		// return (Point) new WKTReader().read(wellKnownText);
		point.setSRID(3857);
		return point;
	}

	public static LineString wktToGeometryLine(String wellKnownText) throws ParseException {

		LineString line = (LineString) new WKTReader().read(wellKnownText);
		// return (Point) new WKTReader().read(wellKnownText);
		line.setSRID(3857);
		return line;
	}

	private static Double transformTometer(Double distance) {

		String distanceString = distance.toString();

		String removeFirstNumber = distanceString.substring(2, 7);

		Double distanceTransformed = new Double(removeFirstNumber);

		return distanceTransformed;
	}

	public static List<StreetsBean> transformFromStreetsEntityList(Iterable<StreetsEntity> streetsEntityList) {
		List<StreetsBean> streetsBeanList = new ArrayList<>();

		for (StreetsEntity streetsEntity : streetsEntityList) {
			StreetsBean streetsBean = transformFromStreetsEntity(streetsEntity);
			streetsBeanList.add(streetsBean);
		}
		return streetsBeanList;
	}

	private static StreetsBean transformFromStreetsEntity(StreetsEntity streetsEntity) {
		
		StreetsBean streetsBean = new StreetsBean();
		
		streetsBean.setGeom(streetsEntity.getGeom().toString());
		
		
		return streetsBean;
	}

}
