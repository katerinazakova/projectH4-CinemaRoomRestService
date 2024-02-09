package com.github.katerinazakova.projectH4CinemaRoomRestService.entity.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.InputMismatchException;

@ControllerAdvice
public class CinemaExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomBadRequestException.class})

    public ResponseEntity<ErrorResponse> cinemaExceptionResponseBadRequest(CustomBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }


    @ExceptionHandler({CustomUnauthorizedException.class})

    public ResponseEntity<ErrorResponse> cinemaExceptionResponseUnauthorized(CustomUnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage()));
    }


}
