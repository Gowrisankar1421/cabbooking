package com.example.cabbooking.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabbooking.dto.CabDto;
import com.example.cabbooking.dto.CabResponseDto;
import com.example.cabbooking.service.CabService;

/**
 * This is class for cab functionality
 * 
 * @author Gowri sankar
 *  @version 1.0
 * @since 2020-06-11
 *
 */
@RestController
@RequestMapping("/cabs")
public class CabController {
	
	Logger logger = LoggerFactory.getLogger(CabController.class);
	
	@Autowired
	CabService cabService;
	
	/**
	 * this is for adding cab
	 * @param cabDto
	 * @return String
	 */

	@PostMapping("")
	public ResponseEntity<String> addCab(@Valid @RequestBody CabDto cabDto) {
		logger.info("<----------------<inside addCab method>--------------------->");
		cabService.addCab(cabDto);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}

	/**
	 * this is for getting cab details
	 * @param date
	 * @return List<CabResponseDto>
	 */
	@GetMapping("/search")
	public ResponseEntity<List<CabResponseDto>> getCabDetails(@RequestParam(name = "localDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		logger.info("<----------------<inside getCabDetails method>--------------------->");
		List<CabResponseDto> cabResponseDtos = cabService.getCabDetails(date);
		return new ResponseEntity<>(cabResponseDtos,HttpStatus.OK);
	}
	

}
