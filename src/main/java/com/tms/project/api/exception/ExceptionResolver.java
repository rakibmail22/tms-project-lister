package com.tms.project.api.exception;

import com.tms.project.api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResolver {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
	public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
		return new ErrorResponse(ex.getResourceName(), ex.getMsg());
	}
}
