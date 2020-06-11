package com.example.cabbooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cabbooking.dto.CabDto;
import com.example.cabbooking.dto.CabResponseDto;
import com.example.cabbooking.model.Cab;
import com.example.cabbooking.repository.CabRepository;
/**
 * @author Gowri sankar
 * @version 1.0
 * @since 2020-06-11
 *
 */
@Service
public class CabService {
	
	Logger logger = LoggerFactory.getLogger(CabService.class);

	@Autowired
	CabRepository cabRepository;

	public void addCab(CabDto cabDto) {
		logger.info("<------------------------<add cab booking>-------------------------->");
		Cab cab = new Cab();
		BeanUtils.copyProperties(cabDto, cab);
		cab.setAvailableStatus("available");
		cabRepository.save(cab);
	}

	public List<CabResponseDto> getCabDetails(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		List<CabResponseDto> cabResponseDtos = new ArrayList<>();
		List<Cab> cabs = null;
		if (LocalDate.now().isEqual(localDate)) {
			LocalDateTime startLocalDateTime = LocalDateTime.now();
			LocalDateTime endLocalDateTime = localDate.atTime(23, 59);
			cabs = cabRepository.findAllByDateTimeBetween(startLocalDateTime, endLocalDateTime);
		} else {
			LocalDateTime startLocalDateTime = localDate.atStartOfDay();
			LocalDateTime endLocalDateTime = localDate.atTime(23, 59);
			cabs = cabRepository.findAllByDateTimeBetween(startLocalDateTime, endLocalDateTime);
		}
		CabResponseDto cabResponseDto = null;
		for (Cab cab : cabs) {
			cabResponseDto = new CabResponseDto();
			LocalDateTime dbLocalDateTime = cab.getDateTime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			BeanUtils.copyProperties(cab, cabResponseDto);
			cabResponseDto.setDateTime(dbLocalDateTime.format(formatter));
			cabResponseDtos.add(cabResponseDto);
		}
		return cabResponseDtos;

	}

}