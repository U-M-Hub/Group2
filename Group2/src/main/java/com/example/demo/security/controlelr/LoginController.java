package com.example.demo.security.controlelr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.security.form.LoginForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@GetMapping("/login")
	public String login(@ModelAttribute LoginForm loginForm) {
		return "loginPackage/login";
	}
	
	@GetMapping("test")
	public String test() {
		return "loginPackage/test";
	}
}
