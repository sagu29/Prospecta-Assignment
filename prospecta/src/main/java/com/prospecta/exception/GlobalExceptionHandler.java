package com.prospecta.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> generalExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), request.getDescription(false) , LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(EntryException.class)
    public ResponseEntity<ErrorDetails> EntryException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), request.getDescription(false) , LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
