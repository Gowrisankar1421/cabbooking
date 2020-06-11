package com.example.cabbooking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.example.cabbooking.dto.CabDto;
import com.example.cabbooking.dto.CabResponseDto;
import com.example.cabbooking.service.CabService;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class CabControllerTest {
	@InjectMocks
	CabController cabController;
	
	@Mock
	CabService cabService;
	
	@Test
	public void testAddCab() {
		CabDto cabDto = new CabDto();
		cabService.addCab(cabDto);
		ResponseEntity<String> response = cabController.addCab(cabDto);
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetCabDetails() {
		Date date = new Date();
		List<CabResponseDto> cabResponseDtos = new ArrayList<>();
		Mockito.when(cabService.getCabDetails(date)).thenReturn(cabResponseDtos);
		Assert.assertNotNull(cabResponseDtos);
		ResponseEntity<List<CabResponseDto>> response = cabController.getCabDetails(date);
		Assert.assertNotNull(response.getBody());
	}

}
