package com.iitu.lesson.demo3.config;

import com.iitu.lesson.demo3.domain.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRequest(ResponseStatusException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getStatusCode().toString(), e.getReason()), e.getStatusCode());
    }
}
