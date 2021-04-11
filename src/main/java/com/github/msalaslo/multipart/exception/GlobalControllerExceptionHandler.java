package com.github.msalaslo.multipart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.github.msalaslo.multipart.dto.ErrorDto;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	public static final String ERROR_PROCESSING_IMAGE_CODE = "100"; 

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> internalError(RuntimeException ex) {
    	ErrorDto error = ErrorDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.toString()).description(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ImageProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> internalError(ImageProcessingException ex) {
    	ErrorDto error = ErrorDto.builder().code(ERROR_PROCESSING_IMAGE_CODE).description(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ErrorDto> unsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
    	ErrorDto error = ErrorDto.builder().code(ERROR_PROCESSING_IMAGE_CODE).description(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> badRequest(BadRequest ex) {
    	ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.toString()).description(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
}
