package com.jsp.UserService.exception.gobalexception;

import com.jsp.UserService.data.dto.ApiResponse;
import com.jsp.UserService.exception.ResourceNotFoundException;
import com.jsp.UserService.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseUtil.getNotFoundResponse(e.getMessage());
    }
}
