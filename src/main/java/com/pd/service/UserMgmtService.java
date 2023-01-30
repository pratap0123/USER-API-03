package com.pd.service;

import java.util.Map;

import com.pd.binding.LoginForm;
import com.pd.binding.UnlockAccountForm;
import com.pd.binding.UserForm;

public interface UserMgmtService {

	public String checkEmail(String email);// 1

	public Map<Integer, String> getCountryNames();// 2

	public Map<Integer, String> getStateNames(Integer countryId);// 3

//	public Map<Integer, String> getStateName(Integer countryId);

	public Map<Integer, String> getCityNames(Integer stateId);// 4

	public String registerUser(UserForm userForm);// 5

	public String unlockaccount(UnlockAccountForm unlockaccount);// 6 update the password and status

	public String login(LoginForm loginForm);// 7

	public String forgetPwd(String email);// 8

}
