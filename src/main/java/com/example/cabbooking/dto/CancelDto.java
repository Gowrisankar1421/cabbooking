package com.example.cabbooking.dto;

import javax.validation.constraints.NotEmpty;

public class CancelDto {
	
	@NotEmpty
	private long bookingId;
	
	@NotEmpty
	private long userId;
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

}
