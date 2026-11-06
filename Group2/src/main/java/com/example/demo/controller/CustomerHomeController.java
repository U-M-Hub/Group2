package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Books;
import com.example.demo.mapper.CustomerHomeMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerHomeController {
	
	private final CustomerHomeMapper customerHomeMapper;
	
	@GetMapping("/home")
	public String customerHome(Model model) {
		//新着書籍を取得
		List<Books> newBooks = customerHomeMapper.selectNewBooks();
		//HTMLに新着書籍を渡す
		model.addAttribute("newBooks", newBooks);
		return "customerHome";
	}

}
