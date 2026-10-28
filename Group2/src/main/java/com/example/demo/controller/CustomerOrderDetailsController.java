package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CustomerOrderDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerOrderDetailsController {

	// この場合、に表示するURLは
	//   @RequestMapping("/customer")
	//   @GetMapping("")
	// の為、URLは http://localhost:8080/customer 
	// URL構成が決まったら該当する内容に変更する必要あり。
	
	// DI
	private final CustomerOrderDetailsService customerOrderDetailsService;

	/* 消費者：ユーザーIDで取得 */
//	@GetMapping("/{id}")
	@GetMapping("/orders")
	public String showOederDetailsByUserId(Model model) {
		model.addAttribute("message", "注文履歴一覧");
//		model.addAttribute("DetailsByUserId", customerOrderDetailsService.getOrderDetailsByUserId(id));
		model.addAttribute("DetailsByUserId", customerOrderDetailsService.getOrderDetailsByUserId(1));
		return "/customerOrderDetails";	// 使用html；customerOrderDetails.html
	}	
	
}
