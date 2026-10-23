package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.OrderDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class OrderDetailsController {

	// DI
	private final OrderDetailsService orderDetailsService;
	

	/* 商品管理者：全件取得 */
	@GetMapping("/all")
	public String showAllOederDetails(Model model) {
		model.addAttribute("message", "注文履歴一覧");
		model.addAttribute("allDetails", orderDetailsService.getAllOrderDetails());
//		return "list";
		return "";
	}	

	/* 消費者：ユーザーIDで取得 */
//	@GetMapping("/all/{id}")
	@GetMapping("/all/1")
	public String showOederDetailsByUserId(Model model) {
		model.addAttribute("message", "注文履歴一覧");
//		model.addAttribute("DetailsByUserId", orderDetailsService.getOrderDetailsByUserId(id));
		model.addAttribute("DetailsByUserId", orderDetailsService.getOrderDetailsByUserId(1));
		return "/orderdetails-c";
	}	

}
