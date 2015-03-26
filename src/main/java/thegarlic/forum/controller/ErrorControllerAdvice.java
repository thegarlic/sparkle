package thegarlic.forum.controller;

import lombok.Getter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import thegarlic.forum.exception.DefaultException;

@ControllerAdvice
public class ErrorControllerAdvice {
	
	@ExceptionHandler(value = DefaultException.class)
	public ResponseEntity<?> error(DefaultException e) {
		return new ResponseEntity<>(new Error(e), e.getStatus());
	}
	
	static class Error {
		
		@Getter
		private Integer statusCode;
		@Getter
		private String message;
		
		public Error(DefaultException e) {
			this.message = e.getMessage();
			this.statusCode = e.getStatus().value();
		}
	}

}
