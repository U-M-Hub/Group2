package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.AdminOrderDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminOrderDetailsController {

	// この場合、に表示するURLは
	//   @RequestMapping("/admin")
	//   @GetMapping("/orders")
	// の為、URLは http://localhost:8080/admin/orders
	// URL構成が決まったら該当する内容に変更する必要あり。

	// DI
	private final AdminOrderDetailsService adminOrderDetailsService;

	/* 商品管理者：全件取得 */
	@GetMapping("/orders")
	public String showAllOederDetails(Model model) {
		model.addAttribute("message", "注文履歴一覧(商品管理者)");
		model.addAttribute("allDetails", adminOrderDetailsService.getAllOrderDetails());
		return "/adminOrderDetails";	// 使用html；adminOrderDetails.html
	}	
	
}
