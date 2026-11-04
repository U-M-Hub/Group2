package com.example.demo.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.RegisterMapper;
import com.example.demo.security.form.RegisterForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	private final RegisterMapper registerMapper;
	private final PasswordEncoder passwordEncoder;
	
	public void registerUser(RegisterForm registerForm) {
		
		String pass = passwordEncoder.encode(registerForm.getUserPass());
		
		registerMapper.registerUser(registerForm.getUserName(), pass);
	}
}
