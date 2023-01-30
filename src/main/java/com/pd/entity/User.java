package com.pd.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_DTLS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_FIRST_NAME")
	private String firstName;
	@Column(name = "USER_LAST_NAME")
	private String lastName;
	@Column(name = "USER_EMAIL")
	private String email;
	@Column(name = "USER_PHON")
	private Integer phon;
	@Column(name = "USER_GENDER")
	private String gender;
	@Column(name = "CITY_ID")
	private Integer cityId;
	@Column(name = "USER_DOB")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dob;
	@Column(name = "C_ID")
	private Integer countryId;
	@Column(name = "STATE_ID")
	private Integer stateId;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "USER_ACC_STATUS")
	private String userAccStatus;

}
