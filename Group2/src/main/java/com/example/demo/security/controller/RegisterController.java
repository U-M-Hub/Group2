package com.example.demo.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.LoginUser;
import com.example.demo.security.form.RegisterForm;
import com.example.demo.security.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

	private final RegisterService registerService;
	
	@GetMapping("/get")
	public String registerGet(RegisterForm registerForm,
							  @AuthenticationPrincipal LoginUser loginUser) {
		
		if(loginUser != null) {
			return "redirect:/login?guest";
		}
		
		return "loginPackage/register";
	}
	
	@PostMapping("/post")
	public String registerPost(@Validated RegisterForm registerForm,
							   BindingResult bindingResult) {
		
		//エラー情報があれば表示
		if(bindingResult.hasErrors()) {
			return "loginPackage/register";
		}
		
		/*ユーザー登録処理のために必要な情報をServiceへ渡して登録処理を行う。
		 * Service側でユーザー名の重複が確認された場合、エラー情報を追加して表示
		 */
		
		//登録処理↓
		if(!registerService.registerUser(registerForm)) {
			//重複していたらエラー情報をbindingResultに追加
			bindingResult.rejectValue("userName",
					  				  "duplicate",
					  				  "そのユーザー名は既に使用されています");
			return "loginPackage/register";
		}
		
		return "redirect:/register/get?success";
	}
}
