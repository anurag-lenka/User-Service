package com.jsp.UserService.dao.service;

import com.jsp.UserService.data.entities.User;

import java.util.List;

public interface UserDAOService {

    User createUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    void deleteUser(String userId);
}
