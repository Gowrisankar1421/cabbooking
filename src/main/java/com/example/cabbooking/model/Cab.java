package com.example.cabbooking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "cab")
public class Cab implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long cabId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String regNumber;
	
	private String availableStatus;
	
	private LocalDateTime dateTime;

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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(String availableStatus) {
		this.availableStatus = availableStatus;
	}
	
	
}