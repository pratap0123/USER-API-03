package com.pd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.entity.UserCity;

public interface CityRepository extends JpaRepository<UserCity, Integer> {
	
	@Query("select cityName from UserCity where stateId=:stateId")
	public List<String> getCities(Integer stateId);
	
	//select * from city_master where stateId=?
	public List<UserCity> findByStateId(Integer stateId);
}
