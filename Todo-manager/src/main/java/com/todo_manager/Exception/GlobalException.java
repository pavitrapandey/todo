package com.todo_manager.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    Logger logger= LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullPointerHandler(NullPointerException e){
        logger.info("Null pointer exception occurred from Global Exception Handler : "+e.getMessage());
        return new ResponseEntity<>("Global server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(ResourceNotFoundException e){
        logger.error("Resource not found exception occurred from Global Exception Handler : "+e.getMessage());
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setSuccess(false);
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }
}
