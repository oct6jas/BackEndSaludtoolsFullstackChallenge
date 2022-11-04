package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<BasicExceptionDto> handleConflict(BasicException ex) {
        ex.printStackTrace();
        BasicExceptionDto dto = new BasicExceptionDto();
        dto.setCode(ex.getCode());
        dto.setMenssage(ex.getMessage());

        return new ResponseEntity<BasicExceptionDto>(dto, HttpStatus.BAD_REQUEST);
    }

}