package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CustomerCartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerCartController {

	// この場合、に表示するURLは
	//   @RequestMapping("/customer")
	//   @GetMapping("/cart")
	// の為、URLは http://localhost:8080/customer/cart
	// URL構成が決まったら該当する内容に変更する必要あり。

	
    private final CustomerCartService customerCartService;

    // 一覧画面表示
    @GetMapping("/cart")
    public String showCartByUserId(Model model) {

        model.addAttribute(
            "cartList",
            customerCartService.listBooksInCartAll()
        );

        return "customer/cart";
    }
	
	
}
