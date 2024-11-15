package com.jsp.UserService.dao.impl;

import com.jsp.UserService.dao.service.UserDAOService;
import com.jsp.UserService.data.entities.User;
import com.jsp.UserService.exception.ResourceNotFoundException;
import com.jsp.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAOServiceImp implements UserDAOService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid userId"+ userId));
    }

    @Override
    public void deleteUser(String userId) {

        userRepository.deleteById(userId);
    }
}
