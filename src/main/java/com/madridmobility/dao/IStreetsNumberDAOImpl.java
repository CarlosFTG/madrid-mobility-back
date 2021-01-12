package com.madridmobility.dao;

import java.util.List;

import com.madridmobility.Entities.StreetsNumberEntity;
import com.madridmobility.beans.StreetsNumberBean;

public interface IStreetsNumberDAOImpl {

	List<StreetsNumberEntity> findAll();

}
