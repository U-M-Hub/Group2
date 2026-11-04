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
		
		//ユーザー名重複チェック処理の結果に応じて、エラー情報を追加
		if(registerService.checkDuplicateUserName(registerForm.getUserName())) {
			bindingResult.rejectValue("userName",
									  "duplicate",
									  "そのユーザー名は既に使用されています");
		}
		
		//エラー情報があれば表示
		if(bindingResult.hasErrors()) {
			return "loginPackage/register";
		}
		
		/*
		 * ユーザー登録処理のために必要な情報をServiceへ渡し、登録処理
		 * 万が一、同時登録などでチェックをすり抜けてINSERTが行われた場合にも、
		 * エラー情報を追加。
		 */
		if(!registerService.registerUser(registerForm)) {
			bindingResult.rejectValue("userName",
					  				  "duplicate",
					  				  "そのユーザー名は既に使用されています");
			return "loginPackage/register";
		}
		
		return "redirect:/register/get?success";
	}
}
