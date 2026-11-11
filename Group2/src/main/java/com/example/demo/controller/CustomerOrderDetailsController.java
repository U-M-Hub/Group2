package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.LoginUser;
import com.example.demo.service.CustomerOrderDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerOrderDetailsController {

	// この場合、に表示するURLは
	//   @RequestMapping("/customer")
	//   @GetMapping("/orders")
	// の為、URLは http://localhost:8080/customer/orders
	// URL構成が決まったら該当する内容に変更する必要あり。
	
	// DI
	private final CustomerOrderDetailsService customerOrderDetailsService;

	/* 消費者：ユーザーIDで取得 */
	@GetMapping("/orders")
	public String showOederDetailsByUserId(
			@AuthenticationPrincipal LoginUser loginUser,
			Model model
			) {

		// ログインIDの取得
		Integer loginId	= loginUser.getUserId();
		
		model.addAttribute("message", "【消費者】注文履歴");
		model.addAttribute("DetailsByUserId", customerOrderDetailsService.getOrderDetailsByUserId(loginId));
//		model.addAttribute("DetailsByUserId", customerOrderDetailsService.getOrderDetailsByUserId(1));
		return "/customerOrderDetails";	// 使用html；customerOrderDetails.html
	}	
	
}
