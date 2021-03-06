package com.example.cabbooking.dto;

import javax.validation.constraints.NotEmpty;

public class BookingSearchDto {
	
	@NotEmpty
	private long userId;
	
	@NotEmpty
	private int month;
	
	@NotEmpty
	private int year;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
