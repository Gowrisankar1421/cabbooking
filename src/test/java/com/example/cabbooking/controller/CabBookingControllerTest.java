package com.example.cabbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

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

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class CabBookingControllerTest {
	@InjectMocks
	CabBookingController cabBookingController;
	@Mock
	CabBookingService cabBookingService;
	
	@Test
	public void testCabBooking() throws CabNotFoundException, UserNotFoundException, BookingNotpossibleException {
		BookingDto bookingDto = new BookingDto();
		ResponseDto1 responseDto =new ResponseDto1();
		Mockito.when(cabBookingService.bookCab(bookingDto)).thenReturn(responseDto);
		Assert.assertNotNull(responseDto);
		ResponseEntity<ResponseDto1> response = cabBookingController.cabBooking(bookingDto);
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testCancelBooking() throws CabNotFoundException, UserNotFoundException, CancelNotpossibleException {
		CancelDto cancelDto = new CancelDto();
		ResponseDto responseDto= new ResponseDto();
		Mockito.when(cabBookingService.cancelBooking(cancelDto)).thenReturn(responseDto);
		Assert.assertNotNull(responseDto);
		ResponseEntity<ResponseDto> response=cabBookingController.cancelBooking(cancelDto);
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testHistory() throws UserNotFoundException {
		BookingSearchDto bookingSearchDto = new BookingSearchDto();
		List<BookingServiceDto> bookingServiceDtos = new ArrayList<>();
		Mockito.when(cabBookingService.getHistory(bookingSearchDto)).thenReturn(bookingServiceDtos);
		Assert.assertNotNull(bookingServiceDtos);
		ResponseEntity<List<BookingServiceDto>> responses = cabBookingController.history(bookingSearchDto);
		Assert.assertNotNull(responses.getBody());
	}

}
