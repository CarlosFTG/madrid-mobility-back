package com.madridmobility.services;

import java.util.List;

import com.madridmobility.Entities.BikeAccidentEntity;
import com.madridmobility.beans.BikeAccidentBean;

public interface IBikeAccidentService {

	public List<BikeAccidentBean> getBikeAccidentes();
}
