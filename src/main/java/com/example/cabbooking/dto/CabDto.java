package com.example.cabbooking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

public class CabDto {

	@NotEmpty
	private String name;

	@NotEmpty
	private String regNumber;

	@NotEmpty
	private LocalDateTime dateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}

