package com.example.cabbooking.controller;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.dto.UserRegistrationDto;
import com.example.cabbooking.service.UserService;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Test
	public void testUserRegister() throws NoSuchAlgorithmException {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		UserDto userDto = new UserDto();
		Mockito.when(userService.createUser(userRegistrationDto)).thenReturn(userDto);
		Assert.assertNotNull(userDto);
		ResponseEntity<UserDto> response = userController.userRegister(userRegistrationDto);
		Assert.assertNotNull(response.hasBody());
		
	}

	@SuppressWarnings("null")
	@Test(expected=NullPointerException.class)
	public void testUserRegisterExcep() throws NoSuchAlgorithmException {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		UserDto userDto = new UserDto();
		Mockito.when(userService.createUser(userRegistrationDto)).thenReturn(userDto);
		Assert.assertNotNull(userDto);
		ResponseEntity<UserDto> response = null;
		Mockito.when(userController.userRegister(userRegistrationDto)).thenReturn(response);
		Assert.assertNull(response.hasBody());
		
	}
}
