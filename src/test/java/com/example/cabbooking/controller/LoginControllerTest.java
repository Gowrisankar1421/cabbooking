package com.example.cabbooking.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.example.cabbooking.dto.ResponseMessageDto;
import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.exceptions.UserNotFoundException;
import com.example.cabbooking.service.LoginService;
import com.example.cabbooking.service.LoginServiceImpl;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {
	@InjectMocks
	LoginController loginController;
	@Mock
	LoginServiceImpl loginServiceImpl;
	@Mock
	LoginService loginService;
	
	@Test
	public void testLogin() throws UserNotFoundException {
		UserDto userDto = new UserDto();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		Mockito.when(loginService.login(userDto)).thenReturn(responseMessageDto);
		Assert.assertNotNull(responseMessageDto);
		ResponseEntity<ResponseMessageDto> response=loginController.login(userDto);
		Assert.assertNotNull(response);
		
	}

	@Test(expected=UserNotFoundException.class)
	public void testLoginExcep() throws UserNotFoundException {
		UserDto userDto = new UserDto();
		userDto.setEmail("gs@gmail.com");
		userDto.setPassword("hai");
		Mockito.when(loginService.login(userDto)).thenThrow(new UserNotFoundException(null));
		ResponseEntity<ResponseMessageDto> response=loginController.login(userDto);
		Assert.assertNotNull(response);
		
	}

}
