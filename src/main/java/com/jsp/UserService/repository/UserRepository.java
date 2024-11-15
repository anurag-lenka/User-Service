package com.jsp.UserService.repository;

import com.jsp.UserService.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
