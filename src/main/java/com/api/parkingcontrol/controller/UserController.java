package com.api.parkingcontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.model.User;
import com.api.parkingcontrol.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder; 
	
	@GetMapping
	public ResponseEntity<List<User>> list() {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<User> add(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return ResponseEntity.ok(userRepository.save(user));
	}

}
