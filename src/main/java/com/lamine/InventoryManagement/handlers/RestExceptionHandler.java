package com.lamine.InventoryManagement.handlers;

import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException (EntityNotFoundException exception , WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND ;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(notFound.value())
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto , notFound);
    }

    @ExceptionHandler(EntityInvalidException.class)
    public ResponseEntity<ErrorDto> handleException (EntityInvalidException exception , WebRequest webRequest){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST ;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(badRequest.value())
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDto , badRequest);
    }




}
