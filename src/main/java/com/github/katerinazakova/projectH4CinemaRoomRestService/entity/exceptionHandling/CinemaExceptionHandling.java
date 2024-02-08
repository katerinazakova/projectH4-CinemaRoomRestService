package com.github.katerinazakova.projectH4CinemaRoomRestService.entity.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CinemaExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomBadRequestException.class})

    public ResponseEntity<Object> cinemaExceptionResponseBadRequest(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({CustomUnauthorizedException.class})

    public ResponseEntity<Object> cinemaExceptionResponseUnauthorized(CustomUnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage()));
    }


}
