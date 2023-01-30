package com.pd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.entity.UserCountry;

public interface CountryRepository extends JpaRepository<UserCountry, Integer> {

	@Query("select c from UserCountry c ")
	public List<UserCountry> getCountryNames();

}
