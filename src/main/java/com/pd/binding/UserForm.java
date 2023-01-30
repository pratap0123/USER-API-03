package com.pd.binding;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserForm {

	private Integer userId;

	private String firstName;

	private String lastName;

	private String email;

	private Integer phon;

	private String gender;

	private Integer cityId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dob;

	private Integer countryId;

	private Integer stateId;

	private String userPassword;

	private String userAccStatus;
}
