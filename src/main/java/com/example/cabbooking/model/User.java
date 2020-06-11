package com.example.cabbooking.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long userId;
 
 @Column(unique=true)
 @NotNull(message="email should not be null")
 private String email;

 @NotEmpty
 private String password;

 private String firstname;

 private String lastname;

 @NotEmpty
 private String phoneNumber;

 @JsonIgnore
 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 private List<Booking> bookings;

 public Long getUserId() {

 return userId;

 }

 public void setUserId(Long userId) {

 this.userId = userId;

 }

 public String getEmail() {

 return email;

 }

 public void setEmail(String email) {

 this.email = email;

 }

 public String getPassword() {

 return password;

 }

 public void setPassword(String password) {

 this.password = password;

 }

 public String getFirstname() {

 return firstname;

 }

 public void setFirstname(String firstname) {

 this.firstname = firstname;

 }

 public String getLastname() {

 return lastname;

 }

 public void setLastname(String lastname) {

 this.lastname = lastname;

 }

 public String getPhoneNumber() {

 return phoneNumber;

 }

 public void setPhoneNumber(String phoneNumber) {

 this.phoneNumber = phoneNumber;

 }

 public List<Booking> getBookings() {

 return bookings;

 }

 public void setBookings(List<Booking> bookings) {

 this.bookings = bookings;

 }

}

