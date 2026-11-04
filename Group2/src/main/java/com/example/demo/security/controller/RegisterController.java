package com.example.demo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.form.RegisterForm;
import com.example.demo.security.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

	private final RegisterService registerService;
	
	@GetMapping("/get")
	public String registerGet(RegisterForm registerForm) {
		
		return "loginPackage/register";
	}
	
	@PostMapping("/post")
	public String registerPost(@Validated RegisterForm registerForm,
							   BindingResult bindingResult) {
		
		if(registerService.checkDuplicateUserName(registerForm.getUserName())) {
			bindingResult.rejectValue("userName",
									  "duplicate",
									  "そのユーザー名は既に使用されています");
		}
		
		if(bindingResult.hasErrors()) {
			return "loginPackage/register";
		}
		
		registerService.registerUser(registerForm);
		
		return "redirect:/register/get?success";
	}
}
