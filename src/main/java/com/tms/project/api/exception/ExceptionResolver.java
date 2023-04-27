package com.tms.project.api.exception;

import com.tms.project.api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionResolver {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
	public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
		return new ErrorResponse(ex.getResourceName(), ex.getMsg());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors()
		         .stream()
		         .map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
		         .toList();
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ConfigurationAlreadyExistsException.class)
	public ErrorResponse handleMultiConfiguration(ConfigurationAlreadyExistsException ex) {
		return new ErrorResponse(ex.getResourceName(), ex.getMsg());
	}


}
