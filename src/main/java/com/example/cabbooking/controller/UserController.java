package com.example.cabbooking.controller;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.dto.UserRegistrationDto;
import com.example.cabbooking.service.UserService;

/**
 * This class is for user functionality
 * 
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * this is registration of user
	 * 
	 * @param userRegistrationDto
	 * @return UserDto
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseEntity<UserDto> userRegister(@Validated @RequestBody UserRegistrationDto userRegistrationDto) throws NoSuchAlgorithmException {
		logger.info("<----------------<inside createUser method>--------------->");
		UserDto userDto = userService.createUser(userRegistrationDto);
		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}

}
