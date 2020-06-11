package com.example.cabbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cabbooking.dto.ResponseMessageDto;
import com.example.cabbooking.dto.UserDto;
import com.example.cabbooking.exceptions.UserNotFoundException;
import com.example.cabbooking.model.User;
import com.example.cabbooking.repository.UserRepository;

/**
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	UserRepository loginRepository;

	/**
	 * @throws UserNotFoundException;
	 * @return  ResponseMessageDto
	 */
	public ResponseMessageDto login(UserDto userDto) throws UserNotFoundException {
		logger.info("<----------------------------<inside login method>--------------------------------->");
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
	
		logger.debug("<------------------------<debug for user with emailId,password>------------------------->");
		User user = loginRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		if (user==null) {
			logger.warn("<---------------------<user not found >---------------------->");
			throw new UserNotFoundException("User doesnot exists");
		}
		responseMessageDto.setMessage("user Logged in Sucessfully");
		return responseMessageDto;
		
	}
		
	}
	


