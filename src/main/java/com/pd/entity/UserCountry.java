package com.pd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUNTRY_MASTER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCountry {
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	@Column(name="COUNTRY_NAME")
	private String cName;

}
