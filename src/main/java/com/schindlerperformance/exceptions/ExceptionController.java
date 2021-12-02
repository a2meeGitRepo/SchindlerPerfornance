package com.schindlerperformance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.schindlerperformance.model.ErrorResponse;

public class ExceptionController {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	    ErrorResponse error = new ErrorResponse();
	    error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	    error.setMessage(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
