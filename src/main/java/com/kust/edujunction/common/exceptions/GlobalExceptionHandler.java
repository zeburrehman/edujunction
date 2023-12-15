package com.kust.edujunction.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) throws Exception {
        return handleException(ex, request);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<Object> handleAll(Exception ex, WebRequest request) throws Exception {
        return handleException(ex, request);
    }

}
