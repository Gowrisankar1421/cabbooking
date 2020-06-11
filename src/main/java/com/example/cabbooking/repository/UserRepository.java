package com.example.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cabbooking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

}
