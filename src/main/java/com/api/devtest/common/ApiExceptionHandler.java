package com.api.devtest.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler{

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity handleApiException(HttpServletRequest request, ApiException e){
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }
}
