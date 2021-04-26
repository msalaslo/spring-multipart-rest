package com.github.msalaslo.multipart.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;

import com.github.msalaslo.multipart.dto.ErrorDto;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	public static final String ERROR_PROCESSING_IMAGE_CODE = "100"; 
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> internalError(RuntimeException throwable, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDto body = ErrorDto.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .code(httpStatus.value() + "") // assuming HttpStatus param.
                .description(httpStatus.getReasonPhrase() + ":" + throwable.getMessage())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }
    
   
    @ExceptionHandler(ImageProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> errorProcessingImage(ImageProcessingException throwable, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDto body = ErrorDto.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .code(ERROR_PROCESSING_IMAGE_CODE) 
                .description(httpStatus.getReasonPhrase() + ":" + throwable.getMessage())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }

    
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ErrorDto> unsupportedMediaType(HttpMediaTypeNotSupportedException throwable) {
        HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        ErrorDto body = ErrorDto.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .code(httpStatus.value() + "") // assuming HttpStatus param.
                .description(httpStatus.getReasonPhrase() + ":" + throwable.getMessage())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }
    
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleException(BadRequest throwable, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDto body = ErrorDto.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .code(httpStatus.value() + "") // assuming HttpStatus param.
                .description(httpStatus.getReasonPhrase() + ":" + throwable.getMessage())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }
    
}
