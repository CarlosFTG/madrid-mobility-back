package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.madridmobility.Entities.StreetsEntity;
import com.madridmobility.Entities.StreetsNumberEntity;

@Repository
public interface IStreetsNumberEntityGenericDAO extends CrudRepository<StreetsNumberEntity, Long> {

}
