package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.madridmobility.Entities.StreetsEntity;
@Repository
public interface IStreetsGenericDAO extends CrudRepository<StreetsEntity, Long> {

}
