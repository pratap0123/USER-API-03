package com.pd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.entity.UserState;

public interface StateRepository extends JpaRepository<UserState, Integer> {

	@Query("select stateId,stateName from UserState  where cId=:countryId")
	public List<Object[]> getStateNames(Integer countryId);

	@Query("select s from UserState s where cId=:countryId")
	public List<UserState> getStateName(Integer countryId);

	//select * from state_master where cid=?
	public List<UserState> findByCountryId(Integer cid);
}
