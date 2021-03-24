package com.tcoveney.sbhibsandbox.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcoveney.sbhibsandbox.models.User;
import com.tcoveney.sbhibsandbox.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserRepository userRepository;
	private PasswordEncoder bCryptPasswordEncoder;
	
	public UserController(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/signup")
	public void signUp(@RequestBody User user, HttpServletResponse response) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

}
