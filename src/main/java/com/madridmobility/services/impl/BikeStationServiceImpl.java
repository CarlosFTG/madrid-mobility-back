package com.madridmobility.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.madridmobility.Entities.BiciMadPerimeterEntity;
import com.madridmobility.Entities.BikeStationEntity;
import com.madridmobility.Entities.UserLocationEntity;
import com.madridmobility.beans.AuthBean;
import com.madridmobility.beans.BiciMadPerimeterBean;
import com.madridmobility.beans.BikeStationBean;
import com.madridmobility.beans.FindNearStationsBean;
import com.madridmobility.converter.ConverterUtil;
import com.madridmobility.dao.IBiciMadPerimeterDAO;
import com.madridmobility.dao.IBikeStationCurrentStateGenericDAO;
import com.madridmobility.dao.IBikeStationDAO;
import com.madridmobility.dao.IBikeStationGenericDAO;
import com.madridmobility.dao.IBufferDAO;
import com.madridmobility.dao.IUserLocationDAO;
import com.madridmobility.services.IBikeStationStationService;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Service
public class BikeStationServiceImpl implements IBikeStationStationService {

	final static Logger logger = Logger.getLogger(BikeStationServiceImpl.class);

	String token = null;

	@Autowired
	IBikeStationGenericDAO bikeStationGenericDAO;

	@Autowired
	IBiciMadPerimeterDAO biciMadPerimeter;

	@Autowired
	IUserLocationDAO userLocationDAO;

	@Autowired
	IBufferDAO bufferDAO;

	@Autowired
	IBikeStationCurrentStateGenericDAO bikeStationCurrentStateGenericDAO;

	@Autowired
	IBikeStationDAO bikeStationDAO;

	@Override
	public List<BikeStationBean> getBikeStationsInfo(String mobilityLabsToken) throws JSONException, ParseException {

		List<BikeStationBean> bikeStationList = new ArrayList<>();

		if (mobilityLabsToken.length() > 0) {

			final String urlGetStations = "https://openapi.emtmadrid.es/v1/transport/bicimad/stations/";

			HttpHeaders getBikesStationsHeaders = new HttpHeaders();

			getBikesStationsHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			getBikesStationsHeaders.setContentType(MediaType.APPLICATION_JSON);

			getBikesStationsHeaders.set("accessToken", mobilityLabsToken);

			HttpEntity<String> getBikesEntityReq = new HttpEntity<String>("parameters", getBikesStationsHeaders);

			RestTemplate getBikesStationsRestTemplate = new RestTemplate();
			ResponseEntity<String> getBikesStationsResult = getBikesStationsRestTemplate.exchange(urlGetStations,
					HttpMethod.GET, getBikesEntityReq, String.class);

			String getBikesResultString = getBikesStationsResult.toString().substring(5,
					getBikesStationsResult.toString().length());

			JSONObject JSONBikesResultResponse = new JSONObject(getBikesResultString);

			JSONArray bikeStationsInfoArray = JSONBikesResultResponse.getJSONArray("data");

			for (int i = 0; i < bikeStationsInfoArray.length(); i++) {
				JSONObject bikeStationJSON = (JSONObject) bikeStationsInfoArray.get(i);

				BikeStationBean bikeStationBean = new BikeStationBean();

				bikeStationBean.setStationId(((String) bikeStationJSON.get("number")));

				bikeStationBean.setAddress(((String) bikeStationJSON.get("address")));

				bikeStationBean.setName((String) bikeStationJSON.get("name"));

				bikeStationBean.setAvailableBikes(Long.parseLong(bikeStationJSON.get("dock_bikes").toString()));

				bikeStationBean.setFreeDocks(Long.parseLong(bikeStationJSON.get("free_bases").toString()));

				bikeStationBean.setReservations((Long.parseLong(bikeStationJSON.get("reservations_count").toString())));

				HashMap<String, String> coordsHashMap = new HashMap<>();

				JSONObject geometryJSON = new JSONObject(bikeStationJSON.get("geometry").toString());

				int indexOfFirstParenthesis = geometryJSON.get("coordinates").toString().indexOf('[');

				int indexOfLastParenthesis = geometryJSON.get("coordinates").toString().indexOf(']');

				String coords = geometryJSON.get("coordinates").toString().substring(indexOfFirstParenthesis + 1,
						indexOfLastParenthesis);

				coordsHashMap.put("coordinates", coords);

				bikeStationBean.setPointsList(coordsHashMap);

				this.bikeStationDAO.updateBikeStation(bikeStationBean);

				bikeStationList.add(bikeStationBean);

			}
		}
		return bikeStationList;
	}

	@Override
	public BiciMadPerimeterBean getBiciMadPerimetre() {

		BiciMadPerimeterEntity biciMadPerimeterEntity;

		BiciMadPerimeterBean biciMadPerimeterBean = new BiciMadPerimeterBean();

		biciMadPerimeterEntity = biciMadPerimeter.findById(new Long(3)).get();

		biciMadPerimeterBean.setCoordinates(biciMadPerimeterEntity.getCoordinates().toString());
		return biciMadPerimeterBean;
	}

	@Override
	public List<BikeStationBean> getClosestStation(FindNearStationsBean findNearStationsBean)
			throws ParseException, JSONException {
		UserLocationEntity userLocationEntity = new UserLocationEntity();

		List<BikeStationBean> bikeStationBean = new ArrayList<>();
		List<BikeStationEntity> bikeStationEntity = new ArrayList<>();

		userLocationEntity.setId(new Long(1));

		userLocationEntity.setCoordinates(wktToGeometry(findNearStationsBean.getCoordinates()));

		this.userLocationDAO.save(userLocationEntity);
		bikeStationEntity = this.bikeStationDAO.getClosestStation(userLocationEntity, findNearStationsBean);
		bikeStationBean = ConverterUtil.transformFromBikeStationEntityList(bikeStationEntity);
		this.userLocationDAO.delete(userLocationEntity);
		return bikeStationBean;
	}

	public static Point wktToGeometry(String wellKnownText) throws ParseException {

		Point point = (Point) new WKTReader().read(wellKnownText);
		point.setSRID(3857);
		return point;
	}

	@Override
	public List<BikeStationBean> saveBikeStations(List<BikeStationBean> bikeStationList)
			throws JSONException, ParseException {

		this.bikeStationGenericDAO.deleteAll();

		this.bikeStationGenericDAO.saveAll(ConverterUtil.transformFromBikeStationBeanList(bikeStationList));
		return null;
	}
}
