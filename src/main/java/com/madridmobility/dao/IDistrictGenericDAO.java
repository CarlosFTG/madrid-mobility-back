package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;

import com.madridmobility.Entities.DistrictEntity;

public interface IDistrictGenericDAO extends CrudRepository<DistrictEntity, Long> {

}
