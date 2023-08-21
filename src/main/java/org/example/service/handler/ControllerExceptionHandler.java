package org.example.service.handler;

import org.example.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity itemNotFoundException(ItemNotFoundException exception,
                                                  HttpServletRequest request){
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
