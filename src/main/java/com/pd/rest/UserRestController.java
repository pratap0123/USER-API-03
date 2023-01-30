package com.pd.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.binding.LoginForm;
import com.pd.binding.UnlockAccountForm;
import com.pd.binding.UserForm;
import com.pd.service.UserMgmtServiceImpl;

@RestController
public class UserRestController {

	@Autowired
	private UserMgmtServiceImpl service;

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginForm loginForm) {

		String login = service.login(loginForm);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@GetMapping("/country")
	public ResponseEntity<Map<Integer, String>> getCountries() {

		Map<Integer, String> countries = service.getCountryNames();
		return new ResponseEntity<>(countries, HttpStatus.OK);
	}

	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer, String>> getStates(@PathVariable Integer countryId) {

		Map<Integer, String> stateNames = service.getStateNames(countryId);
		return new ResponseEntity<>(stateNames, HttpStatus.OK);
	}

	@GetMapping("/cities/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCities(@PathVariable Integer stateId) {

		Map<Integer, String> cityNames = service.getCityNames(stateId);
		return new ResponseEntity<>(cityNames, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<String> checkEmail(@PathVariable String email) {

		String status = service.checkEmail(email);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserForm userForm) {

		String status = service.registerUser(userForm);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@PostMapping("/unlock")
	public ResponseEntity<String> unlockUser(@RequestBody UnlockAccountForm unlockAccountForm) {

		String status = service.unlockaccount(unlockAccountForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/forgot/{email}")
	public ResponseEntity<String> forgotEmail(@PathVariable String email) {

		String status = service.forgetPwd(email);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
