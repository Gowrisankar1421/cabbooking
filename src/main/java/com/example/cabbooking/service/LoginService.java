package com.example.cabbooking.service;

import com.example.cabbooking.dto.ResponseMessageDto;
import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.exceptions.UserNotFoundException;

public interface LoginService {

	ResponseMessageDto login(UserDto userDto) throws UserNotFoundException;

}
