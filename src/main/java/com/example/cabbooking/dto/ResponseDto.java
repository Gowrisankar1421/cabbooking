package com.example.cabbooking.dto;

public class ResponseDto {

	private long bookIngId;
	
	private long cabId;
	
	private long userId;
	
	private String message;

	public long getBookIngId() {
		return bookIngId;
	}

	public void setBookIngId(long bookIngId) {
		this.bookIngId = bookIngId;
	}

	public long getCabId() {
		return cabId;
	}

	public void setCabId(long cabId) {
		this.cabId = cabId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
