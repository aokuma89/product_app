package com.example.demo.product_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.product_app.entity.User;

@Repository
public interface UserRepostiroy extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
