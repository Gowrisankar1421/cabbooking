package com.example.cabbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cabbooking.model.Cab;

public interface CabRepository extends JpaRepository<Cab, Long> {

	List<Cab> findAllByDateTimeBetween(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

}
