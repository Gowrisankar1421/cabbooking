package com.example.cabbooking.dto;

public class CabResponseDto {

	private Long cabId;

	private String name;

	private String regNumber;

	private String dateTime;

	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}

