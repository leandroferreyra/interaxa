package com.interaxa.challenge.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.interaxa.challenge.dto.ErrorDTO;
import com.interaxa.challenge.exceptions.BadRequestExc;


@ControllerAdvice
public class HandlerException {
	
	@ExceptionHandler(BadRequestExc.class)
	public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestExc exc, WebRequest web) {
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(400, exc.getMessage(), web.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}
