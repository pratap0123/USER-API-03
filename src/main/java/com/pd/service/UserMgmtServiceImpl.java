package com.pd.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.binding.LoginForm;
import com.pd.binding.UnlockAccountForm;
import com.pd.binding.UserForm;
import com.pd.entity.User;
import com.pd.entity.UserCity;
import com.pd.entity.UserCountry;
import com.pd.entity.UserState;
import com.pd.repository.CityRepository;
import com.pd.repository.CountryRepository;
import com.pd.repository.StateRepository;
import com.pd.repository.UserRepository;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;

	/*
	 * @Autowired private EmailUtils emailUtils;
	 */

	@Override
	public String checkEmail(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	/*
	 * @Override public Map<Integer, String> getCountryNames() { List<UserCountry>
	 * countries = countryRepo.getCountryNames(); Map<Integer, String> countryMap =
	 * new HashMap<>(); countries.forEach(country -> {
	 * countryMap.put(country.getCountryId(), country.getCName()); }); return
	 * countryMap; }
	 */

	@Override
	public Map<Integer, String> getStateNames(Integer countryId) {

		List<UserState> states = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		states.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCityNames(Integer stateId) {
		List<UserCity> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cities.forEach(city -> {

			cityMap.put(city.getCityId(), city.getCityName());
		});
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {

		// copy data from binding object to entity object
		User entity = new User();
//		BeanUtils.copyProperties(userForm, entity);
		BeanUtils.copyProperties(userForm, entity);
		// generate and Set Password
		entity.setUserPassword(generateRandomPwd());

		// Set Account Status as LOCKED
		entity.setUserAccStatus("LOCKED");
		User save = userRepo.save(entity);
		if (save != null) {

			// Send Email to unlock Account
			/*
			 * String to = entity.getEmail(); String subject = "Registation Email"; String
			 * body = readEmail("REG_RMAIL_BODY.txt", entity); emailUtils.sendMail(to,
			 * subject, body);
			 */
			return "Account Created";
		} else {
			return "Account not Created";
		}
	}

	@Override
	public String unlockaccount(UnlockAccountForm unlockaccount) {

		User user = userRepo.findByEmail(unlockaccount.getEmail());
		if (user != null && user.getUserPassword().equals(unlockaccount.getTempPwd())) {
			user.setUserPassword(unlockaccount.getConfirmPwd());
			user.setUserAccStatus("UNLOCKED");
			userRepo.save(user);
			return "ACCOUNT UNLOCKED";
		}
		return "Invalid Temporary Password";
	}

	@Override
	public String login(LoginForm loginForm) {

		User user = userRepo.findByEmailAndUserPassword(loginForm.getEmail(), loginForm.getPwd());
		if (user == null) {
			return "INVALID CREDINTIALS";
		}

		if (user.getUserAccStatus().equals("LOCKED")) {
			return "ACCOUNT IS LOCKED";
		}
		return "SUCCESS";
	}

	@Override
	public String forgetPwd(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return "ACCOUNT IS NOT FOUND";
		}
		/*
		 * String subject = "Recover Password"; String body =
		 * readEmail("FORGOT_PWD_EMAIL_BODY.txt", user); emailUtils.sendMail(email,
		 * subject, body);
		 */
		return "Password sent to Your Email";
	}

	private String generateRandomPwd() {
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYS1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int pwdLenght = 8;
		for (int i = 0; i < pwdLenght; i++) {

			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));

		}
		return sb.toString();

	}

	private String readEmail(String filename, User user) {

		StringBuilder buffer = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {

				line = line.replace("${FNAME}", user.getFirstName());
				line = line.replace("${LNAME}", user.getLastName());
				line = line.replace("${TEMP_PWD}", user.getUserPassword());
				line = line.replace("${EMAIL}", user.getEmail());
				line = line.replace("${PWD}", user.getUserPassword());
				buffer.append(line);
			});
		} catch (Exception e) {
			e.printStackTrace();

		}
		return buffer.toString();
	}

	//java 8 implementation
	@Override
	public Map<Integer, String> getCountryNames() {
		List<UserCountry> countries = countryRepo.getCountryNames();
		HashMap<Integer, String> map = countries.stream().collect(Collectors
						  .toMap(
								  UserCountry::getCountryId,
								  UserCountry::getCName,
								  (x, y)
                                  -> x + ", " + y,
								  HashMap::new));
		return map;
	}

}
