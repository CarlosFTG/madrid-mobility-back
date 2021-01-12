package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.madridmobility.Entities.BikeStationEntity;
@Repository
public interface IBikeStationGenericDAO extends CrudRepository<BikeStationEntity, Long> {

}
