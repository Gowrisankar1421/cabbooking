package com.example.cabbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabbooking.dto.BookingDto;
import com.example.cabbooking.dto.BookingSearchDto;
import com.example.cabbooking.dto.BookingServiceDto;
import com.example.cabbooking.dto.CancelDto;
import com.example.cabbooking.dto.ResponseDto;
import com.example.cabbooking.dto.ResponseDto1;
import com.example.cabbooking.exceptions.BookingNotpossibleException;
import com.example.cabbooking.exceptions.CabNotFoundException;
import com.example.cabbooking.exceptions.CancelNotpossibleException;
import com.example.cabbooking.exceptions.UserNotFoundException;
import com.example.cabbooking.service.CabBookingService;


/**
 * this is class cab booking functionality
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */
@RestController
@RequestMapping("/bookings")
public class CabBookingController {
	
	Logger logger = LoggerFactory.getLogger(CabBookingController.class);

	@Autowired
	CabBookingService cabBookingService;
	
	/**
	 * this is for cab booking
	 * @param bookingDto
	 * @return ResponseDto1
	 * @throws CabNotFoundException
	 * @throws UserNotFoundException
	 * @throws BookingNotpossibleException
	 */
	@PostMapping("")
	public ResponseEntity<ResponseDto1> cabBooking(@Valid @RequestBody BookingDto bookingDto) throws CabNotFoundException, UserNotFoundException, BookingNotpossibleException{
		logger.info("<------------------------<inside cabBooking method>-------------------->");
		ResponseDto1 responseDto=cabBookingService.bookCab(bookingDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	
	/**
	 * this is cancel booking functionality
	 * @param cancelDto
	 * @return ResponseDto
	 * @throws CabNotFoundException
	 * @throws UserNotFoundException
	 * @throws CancelNotpossibleException
	 */
	@DeleteMapping("/cancel")
	public ResponseEntity<ResponseDto> cancelBooking(@Valid @RequestBody CancelDto cancelDto) throws CabNotFoundException, UserNotFoundException, CancelNotpossibleException {
		logger.info("<------------------------<inside cancelBooking method>-------------------->");
		ResponseDto responseDto=cabBookingService.cancelBooking(cancelDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	/**
	 * this is booking history functionality
	 * @param bookingSearchDto
	 * @return List<BookingServiceDto>
	 * @throws UserNotFoundException
	 * @throws ParseException
	 */
	@PostMapping("/cabhistory")
	public ResponseEntity<List<BookingServiceDto>> history(@Valid @RequestBody BookingSearchDto bookingSearchDto ) throws UserNotFoundException{
		logger.info("<------------------------<inside bookingHistory method>-------------------->");
		List<BookingServiceDto> bookingServiceDtos = cabBookingService.getHistory(bookingSearchDto);
		return new ResponseEntity<>(bookingServiceDtos, HttpStatus.OK);
	}

}
