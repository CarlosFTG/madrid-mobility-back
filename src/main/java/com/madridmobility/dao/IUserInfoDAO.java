package com.madridmobility.dao;

import org.springframework.data.repository.CrudRepository;

import com.madridmobility.Entities.UserInfoEntity;

public interface IUserInfoDAO extends CrudRepository<UserInfoEntity, Long> {

}
