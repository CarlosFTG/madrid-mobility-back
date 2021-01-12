package com.madridmobility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madridmobility.Entities.BikeAccidentEntity;

public interface IBikeAccidentDAO extends JpaRepository<BikeAccidentEntity, Long> {

}
