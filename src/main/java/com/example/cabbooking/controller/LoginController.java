package com.example.cabbooking.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabbooking.dto.ResponseMessageDto;
import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.exceptions.UserNotFoundException;
import com.example.cabbooking.service.LoginService;

/**
 * This class is for login functionality
 * 
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */

@RestController
@RequestMapping("/logins")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	/**
	 * this functionality is for login of user
	 * @param userDto
	 * @return ResponseMessageDto
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<ResponseMessageDto> login(@Valid @RequestBody UserDto userDto) throws UserNotFoundException  {
		logger.info("<--------------------<inside login method>------------------>");
		ResponseMessageDto responseMessageDto = loginService.login(userDto);
		return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);

	}

}
