package com.smart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entity.User;

public interface UserRepositories extends JpaRepository<User,Long> {

	boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
}
