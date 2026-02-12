package com.example.demo.product_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.product_app.entity.User;
import com.example.demo.product_app.repository.UserRepostiroy;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepostiroy userRepostiroy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepostiroy.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("not found"));

		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPasswordHash())
				.authorities(user.getRole())
				.build();
	}
}
