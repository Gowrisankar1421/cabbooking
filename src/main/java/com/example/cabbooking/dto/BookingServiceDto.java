package com.example.cabbooking.dto;

import java.time.LocalDateTime;

import com.example.cabbooking.model.Cab;
import com.example.cabbooking.model.User;

public class BookingServiceDto {
	
	private Long bookingId;
	
	private LocalDateTime bookingTime;
	
	private String bookingStatus;
	
	private Cab cab;
	
	private User user;
	
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Cab getCab() {
		return cab;
	}
	public void setCab(Cab cab) {
		this.cab = cab;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
