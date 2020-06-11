package com.example.cabbooking.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.dto.UserRegistrationDto;
import com.example.cabbooking.model.User;
import com.example.cabbooking.repository.UserRepository;
/**
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */
@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * creating user functionality
	 * @param userRegistrationDto
	 * @return UserDto
	 * @throws NoSuchAlgorithmException 
	 */
	public UserDto createUser(UserRegistrationDto userRegistrationDto) throws NoSuchAlgorithmException {
		logger.info("<-------------------------<inside createUser method>-------------------------->");
		UserDto userDto = new UserDto();
		Random random = SecureRandom.getInstanceStrong();
		User user = new User();
		BeanUtils.copyProperties(userRegistrationDto, user);
		int leftLimit = 97;
		int rightLimit = 122;
		int targetStringLength = 10;

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		user.setPassword(generatedString);
		user = userRepository.save(user);
		BeanUtils.copyProperties(user,userDto);
		return userDto;
	}
	

	}



