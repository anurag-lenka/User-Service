package com.jsp.UserService.service;

import com.jsp.UserService.data.dto.ApiResponse;
import com.jsp.UserService.data.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ApiResponse> createUser(UserDTO userDTO);
    ResponseEntity<ApiResponse> getAllUsers();
    ResponseEntity<ApiResponse> getUser(String userId);
    ResponseEntity<ApiResponse> deleteUser(String userId);
    ResponseEntity<ApiResponse> updateUser(String userId, UserDTO userDTO);
}
