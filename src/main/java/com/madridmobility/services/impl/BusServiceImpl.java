package com.madridmobility.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.madridmobility.beans.ArrivalBean;
import com.madridmobility.beans.BusLineBean;
import com.madridmobility.beans.BusStopBean;
import com.madridmobility.services.IBusService;
import com.vividsolutions.jts.io.ParseException;

@Service
public class BusServiceImpl implements IBusService {

	@Override
	public List<BusStopBean> getBusesStopsInfo(String mobilityLabsToken) throws JSONException, ParseException {
		List<BusStopBean> busStopBeanList = new ArrayList<>();

		if (mobilityLabsToken.length() > 0) {

			final String urlGetBusStops = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/";

			HttpHeaders getBusStopsHeaders = new HttpHeaders();

			getBusStopsHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			getBusStopsHeaders.setContentType(MediaType.APPLICATION_JSON);

			getBusStopsHeaders.set("accessToken", mobilityLabsToken);

			HttpEntity<String> getBusStopsEntityReq = new HttpEntity<String>("parameters", getBusStopsHeaders);

			RestTemplate getBusStopsRestTemplate = new RestTemplate();
			ResponseEntity<String> getBusesStopsResult = getBusStopsRestTemplate.exchange(urlGetBusStops,
					HttpMethod.POST, getBusStopsEntityReq, String.class);
			
			String getBusStopsResultString = getBusesStopsResult.toString().substring(5,
					getBusesStopsResult.toString().length());
			
			JSONObject JSONBusStopsResultResponse = new JSONObject(getBusStopsResultString);
			
			JSONArray busStopsInfoArray = JSONBusStopsResultResponse.getJSONArray("data");
			
			for (int i = 0; i < busStopsInfoArray.length(); i++) {
				
				HashMap<String, String> coordsHashMap = new HashMap<>();
				
				JSONObject busStopJSON = (JSONObject) busStopsInfoArray.get(i);
				
				BusStopBean busStopBean = new BusStopBean();
				
				busStopBean.setName((String) busStopJSON.get("name"));
				
				JSONObject geometryJSON = new JSONObject(busStopJSON.get("geometry").toString());
				
				int indexOfFirstParenthesis = geometryJSON.get("coordinates").toString().indexOf('[');

				int indexOfLastParenthesis = geometryJSON.get("coordinates").toString().indexOf(']');

				String coords = geometryJSON.get("coordinates").toString().substring(indexOfFirstParenthesis + 1,
						indexOfLastParenthesis);

				coordsHashMap.put("coordinates", coords);

				busStopBean.setCoordinates(coordsHashMap);
				
				busStopBeanList.add(busStopBean);
			}
			
		}
		return busStopBeanList;
	}
	
	@Override
	public List<BusStopBean> getBusStopsAroundUser(String mobilityLabsToken,float lng, float lat) throws JSONException, ParseException {
		List<BusStopBean> busStopBeanList = new ArrayList<>();

		if (mobilityLabsToken.length() > 0) {
			
			lng = (float) -3.705096;
			
			lat = (float) 40.413348;

			final String urlGetBusStops = "https://openapi.emtmadrid.es/v2/transport/busemtmad/stops/arroundxy/"+lng+"/"+lat+"/"+100+"/";

			HttpHeaders getBusStopsHeaders = new HttpHeaders();

			getBusStopsHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			getBusStopsHeaders.setContentType(MediaType.APPLICATION_JSON);

			getBusStopsHeaders.set("accessToken", mobilityLabsToken);

			HttpEntity<String> getBusStopsEntityReq = new HttpEntity<String>("parameters", getBusStopsHeaders);

			RestTemplate getBusStopsRestTemplate = new RestTemplate();
			ResponseEntity<String> getBusesStopsResult = getBusStopsRestTemplate.exchange(urlGetBusStops,
					HttpMethod.GET, getBusStopsEntityReq, String.class);
			
			String getBusStopsResultString = getBusesStopsResult.toString().substring(5,
					getBusesStopsResult.toString().length());
			
			JSONObject JSONBusStopsResultResponse = new JSONObject(getBusStopsResultString);
			
			JSONArray busStopsInfoArray = JSONBusStopsResultResponse.getJSONArray("data");
			
			for (int i = 0; i < busStopsInfoArray.length(); i++) {
				
				HashMap<String, String> coordsHashMap = new HashMap<>();
				
				JSONObject busStopJSON = (JSONObject) busStopsInfoArray.get(i);
				
				BusStopBean busStopBean = new BusStopBean();
				
				busStopBean.setBusStopId((int) busStopJSON.get("stopId"));
				
				busStopBean.setArrivalBeanList(timeArrivalBus(mobilityLabsToken,busStopBean.getBusStopId()));
				
				busStopBean.setName((String) busStopJSON.get("address"));
				
				JSONObject geometryJSON = new JSONObject(busStopJSON.get("geometry").toString());
				
				JSONArray busStopsLinesArray = busStopJSON.getJSONArray("lines");
				
				List<BusLineBean> busLineBeanList = new ArrayList();
				
				for (int j = 0; j < busStopsLinesArray.length(); j++) {
					
					BusLineBean busLineBean = new BusLineBean();
					
					JSONObject lineJSON = (JSONObject) busStopsLinesArray.get(j);
					
					//busLineBean.setNameA(busStopsLinesArray.get(j));
					
					busLineBean.setLine((String) lineJSON.get("line"));
					
					busLineBeanList.add(busLineBean);
					
				}
				
				busStopBean.setBusLineBeanList(busLineBeanList);
				
				int indexOfFirstParenthesis = geometryJSON.get("coordinates").toString().indexOf('[');

				int indexOfLastParenthesis = geometryJSON.get("coordinates").toString().indexOf(']');

				String coords = geometryJSON.get("coordinates").toString().substring(indexOfFirstParenthesis + 1,
						indexOfLastParenthesis);

				coordsHashMap.put("coordinates", coords);

				busStopBean.setCoordinates(coordsHashMap);
				
				busStopBeanList.add(busStopBean);
			}
			
		}
		return busStopBeanList;
	}
	
	private List<ArrivalBean> timeArrivalBus(String mobilityLabsToken,int busStopId) {
		
		try {
			
			String busStopIdStr = String.valueOf(busStopId);
			
			final String urlGetBusStops = "https://openapi.emtmadrid.es/v2/transport/busemtmad/stops/"+busStopIdStr+"/arrives/";

			HttpHeaders getBusStopsHeaders = new HttpHeaders();

			getBusStopsHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			getBusStopsHeaders.setContentType(MediaType.APPLICATION_JSON);

			getBusStopsHeaders.set("accessToken", mobilityLabsToken);

			HttpEntity<String> getBusStopsEntityReq = new HttpEntity<String>("parameters", getBusStopsHeaders);

			RestTemplate getBusStopsRestTemplate = new RestTemplate();
			ResponseEntity<String> getBusesStopsResult = getBusStopsRestTemplate.exchange(urlGetBusStops,
					HttpMethod.POST, getBusStopsEntityReq, String.class);
			
			String getBusStopsResultString = getBusesStopsResult.toString().substring(5,
					getBusesStopsResult.toString().length());
			
			System.out.println(getBusStopsResultString);
		}catch(Error e) {
			System.out.println(e);
			return null;
		}finally {
			return null;
		}
	}

}
