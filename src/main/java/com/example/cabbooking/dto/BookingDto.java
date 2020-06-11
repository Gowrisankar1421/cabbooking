package com.example.cabbooking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookingDto {
	
	@NotNull
	private Long userId;
	
	@NotEmpty
	private Long cabId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCabId() {
		return cabId;
	}
	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}


}
