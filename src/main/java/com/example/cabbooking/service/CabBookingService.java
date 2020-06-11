package com.example.cabbooking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cabbooking.dto.BookingDto;
import com.example.cabbooking.dto.BookingSearchDto;
import com.example.cabbooking.dto.BookingServiceDto;
import com.example.cabbooking.dto.CancelDto;
import com.example.cabbooking.dto.ResponseDto;
import com.example.cabbooking.dto.ResponseDto1;
import com.example.cabbooking.exceptions.BookingNotpossibleException;
import com.example.cabbooking.exceptions.CabBookingHistoryNotFound;
import com.example.cabbooking.exceptions.CabNotFoundException;
import com.example.cabbooking.exceptions.CancelNotpossibleException;
import com.example.cabbooking.exceptions.UserNotFoundException;
import com.example.cabbooking.model.Booking;
import com.example.cabbooking.model.Cab;
import com.example.cabbooking.model.User;
import com.example.cabbooking.repository.BookingRepository;
import com.example.cabbooking.repository.CabRepository;
import com.example.cabbooking.repository.UserRepository;
import com.sun.el.parser.ParseException;

/**
 *@author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */
@Service
public class CabBookingService {
	
	Logger logger = LoggerFactory.getLogger(CabBookingService.class);
	private static final String BOOKED = "booked";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	BookingRepository bookingRepository;

	/**
	 * functionality for cancelBooking
	 * @param cancelDto
	 * @return ResponseDto
	 * @throws CabNotFoundException
	 * @throws UserNotFoundException
	 * @throws CancelNotpossibleException
	 */
	public ResponseDto cancelBooking(CancelDto cancelDto) throws CabNotFoundException, UserNotFoundException, CancelNotpossibleException {
		logger.info("<---------------------------<inside service cancelbooking method>------------------------->");
		
		Booking booking;
		User user;
		
		logger.debug("<--------------------------<debug for user in userRepository>----------------------------->");
		
		user=userRepository.findById(cancelDto.getUserId()).orElseThrow(()->new UserNotFoundException("user with given details not found"));
		
		logger.debug("<-------------------<debug for booking using userId and Booking id>----------------------->");
		
		booking = bookingRepository.findBookingByBookingIdAndUser(cancelDto.getBookingId(),user);
		if(booking==null) {
			logger.warn("<-------------------------------<cab bot found exception occured>---------------------------------->");
			throw new CabNotFoundException("booking details is  not found");
		}
		if(booking.getBookingStatus().equalsIgnoreCase(BOOKED)) {
		int booktime = booking.getBookingTime().getHour();
		Cab cab=new Cab();
		
		logger.debug("<------------------------------------<debug for cab using valid cabId>>----------------------------------");
		cab=cabRepository.findById(booking.getCab().getCabId()).orElseThrow(()->new CabNotFoundException("cab with given details not found"));
		int cabtime=cab.getDateTime().getHour();
		if((cabtime-booktime)>1) {
		booking.setBookingStatus("cancelled");
		bookingRepository.save(booking);
		cab.setAvailableStatus("available");
		cabRepository.save(cab);
		ResponseDto response=new ResponseDto();
		response.setBookIngId(booking.getBookingId());
		response.setCabId(booking.getCab().getCabId());
		response.setUserId(booking.getUser().getUserId());
		response.setMessage("booking cancelled successfully");
		return response;
		}
		else {
			ResponseDto response=new ResponseDto();
			response.setBookIngId(booking.getBookingId());
			response.setCabId(booking.getCab().getCabId());
			response.setUserId(booking.getUser().getUserId());
			response.setMessage("Cancellation cannot be done");
			return response;
		}
		}
		else {
			logger.warn("<--------------------------------<cancel Not Possible exception>>---------------------------");
			throw new CancelNotpossibleException(" cancellation already done");
		}
	}
	
	/**
	 * this is booking of booking History
	 * @param bookingSearchDto
	 * @return List<BookingServiceDto>
	 * @throws ParseException
	 * @throws UserNotFoundException
	 */
	public List<BookingServiceDto> getHistory(BookingSearchDto bookingSearchDto) throws UserNotFoundException {
		logger.info("<-----------------------------<inside getHistory method>------------------------>");
		
		logger.debug("<-----------------<debug whether user with given userId is present>----------------->");
		User user = userRepository.findById(bookingSearchDto.getUserId()).orElseThrow(()->new UserNotFoundException("user with given details not found"));
		List<BookingServiceDto> bookitoServiceDtos=new ArrayList<>();
		
		logger.debug("<---------------------------<debug for nooking with given user>------------------------------>");
		List<Booking> bookings= bookingRepository.findBookingByUser(user);
				 if(bookings.isEmpty()) {
					 logger.warn("<------------------------<Cab booking history not found>---------------------------->");
					 throw new CabBookingHistoryNotFound();
				 }
				 else {
					 BookingServiceDto bookingServiceDto = null;
						for (Booking booking : bookings) {
							bookingServiceDto = new BookingServiceDto();
								if(booking.getBookingTime().getMonthValue()==bookingSearchDto.getMonth()&&(booking.getBookingTime().getYear()==bookingSearchDto.getYear())){
										BeanUtils.copyProperties(booking, bookingServiceDto);
										bookitoServiceDtos.add(bookingServiceDto);
									}
								}
							}
							return bookitoServiceDtos;
						
						
				 } 
				
	 /**
	  * functionality for booking cab
	  * @param bookingDto
	  * @return ResponseDto1
	  * @throws CabNotFoundException
	  * @throws UserNotFoundException
	  * @throws BookingNotpossibleException
	  */
	 public ResponseDto1 bookCab(BookingDto bookingDto)	throws CabNotFoundException, UserNotFoundException, BookingNotpossibleException {
		logger.info("<---------------------<inside service book cab method>---------------------->");
		 Booking booking = new Booking();
		ResponseDto1 responseDto1=new ResponseDto1();
		LocalDateTime startLocalDateTime = LocalDateTime.now();
		booking.setBookingTime(startLocalDateTime);
		logger.debug("<--------------------------<debug for user with given userId>--------------------------->");
		User user = userRepository.findById(bookingDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("user with given id not found"));
		booking.setUser(user);
		logger.debug("<--------------------------<debug for cab with given cabId>--------------------------->");
		Cab cab = cabRepository.findById(bookingDto.getCabId()).orElseThrow(()->new CabNotFoundException("cab id not found"));
		booking.setCab(cab);
		if (cab.getAvailableStatus().equalsIgnoreCase("available")) {
			LocalDateTime cabStartTime = cab.getDateTime();
			int cabStartHour = cab.getDateTime().getHour();
			if (cabStartTime.compareTo(startLocalDateTime)<0) {
				
				int localTimeHour = startLocalDateTime.getHour();
				int date=startLocalDateTime.getDayOfMonth();
				int cabdate=cab.getDateTime().getDayOfMonth();
				if(cabdate>=date) {
				int differnece = cabStartHour - localTimeHour;

				if (differnece > 2) {
					booking.setCab(cab);
					booking.setBookingStatus(BOOKED);
					bookingRepository.save(booking);
					responseDto1.setMessage("booking is done successfully");
				} else {
					logger.warn("<-----------------------------<booking not possible because time less than 2 hours>-------------------------------->");
					throw new BookingNotpossibleException(" booking time should be greater than 2 hours but it is "+differnece);
				}
				}
				else {
					logger.warn("<-----------------------------<booking not possible>-------------------------------->");
					throw new BookingNotpossibleException("date not correctly matched");
				}
			} else {
				cab.setAvailableStatus("not available");
				booking.setCab(cab);
				booking.setBookingStatus(BOOKED);
				bookingRepository.save(booking);
				responseDto1.setMessage("booking is done successfully");
			}
		} else {
			logger.warn("<-----------------------------<cab server set cab availablestatus not possible>-------------------------------->");
			throw new BookingNotpossibleException(" status not available");
		}
		return responseDto1;

	}
	
	
}
