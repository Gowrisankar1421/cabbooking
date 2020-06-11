package com.example.cabbooking.dto;

import java.util.Date;



import javax.persistence.Temporal;

import javax.persistence.TemporalType;



public class SearchDto {



 @Temporal(TemporalType.TIMESTAMP)

 private Date timeStamp;



 public Date getTimeStamp() {

 return timeStamp;

 }



 public void setTimeStamp(Date timeStamp) {

 this.timeStamp = timeStamp;

 }



}


