package com.ace.jwtAuth.repository;

import com.ace.jwtAuth.entities.Role;
import com.ace.jwtAuth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByRole(Role role);
}
