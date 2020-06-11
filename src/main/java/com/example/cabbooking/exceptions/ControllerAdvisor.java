package com.example.cabbooking.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final String TIMESTAMP = "timestamp";
	private static final String MESSAGE = "message";
	
	@ExceptionHandler(CabNotFoundException.class)
	public ResponseEntity<Object> handleCabNotFoundException1(CabNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException1(UserNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, " user with given details is not found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CabBookingHistoryNotFound.class)
	public ResponseEntity<Object> handleCabBookingHistoryNotFound(CabBookingHistoryNotFound ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, " booking history is not found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BookingNotpossibleException.class)
	public ResponseEntity<Object> handleNoCabsFoundException(BookingNotpossibleException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CancelNotpossibleException.class)
	public ResponseEntity<Object> handleCancelNotPossibleException(CancelNotpossibleException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, " cancellation is already done");
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}

