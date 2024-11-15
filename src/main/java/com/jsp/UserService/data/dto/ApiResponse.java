package com.jsp.UserService.data.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private HttpStatus httpStatus;
    private int statusCode;
    private Object response;
    
}
