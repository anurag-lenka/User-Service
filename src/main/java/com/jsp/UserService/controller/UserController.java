package com.jsp.UserService.controller;

import com.jsp.UserService.data.dto.ApiResponse;
import com.jsp.UserService.data.dto.UserDTO;
import com.jsp.UserService.data.entities.User;
import com.jsp.UserService.service.UserService;
import com.jsp.UserService.util.ResponseUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping
    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUsers")
    public ResponseEntity<ApiResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    private ResponseEntity<ApiResponse> fallbackUsers(Exception e){
        User user = User.builder().userId("dvcdcdnj13").email("JohnDoe@gamil.com").contact("999999999").name("JohnDoe").build();
        return ResponseUtil.getInternalServerErrorResponse(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getUserByName(@PathVariable String name){
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        return userService.updateUser(userId,userDTO);
    }
}
