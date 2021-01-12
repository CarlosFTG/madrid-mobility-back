package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;

import com.madridmobility.Entities.BikeStationCurrentStateEntity;

public interface IBikeStationCurrentStateGenericDAO extends CrudRepository<BikeStationCurrentStateEntity, Long> {

}
