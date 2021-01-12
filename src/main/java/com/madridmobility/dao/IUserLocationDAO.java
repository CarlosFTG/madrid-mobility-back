package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;

import com.madridmobility.Entities.UserLocationEntity;

public interface IUserLocationDAO extends CrudRepository<UserLocationEntity, Long>{

}
