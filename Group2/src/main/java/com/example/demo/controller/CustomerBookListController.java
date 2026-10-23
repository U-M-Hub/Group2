package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Books;
import com.example.demo.service.CustomerBookListService;

@Controller
public class CustomerBookListController {
	private final CustomerBookListService customerBookListService;

	public CustomerBookListController(
			CustomerBookListService customerBookListService) {
		this.customerBookListService = customerBookListService;
	}

	@GetMapping("/customer/books")
	public String showBookList(Model model) {

		List<Books> books = customerBookListService.findAll();

		model.addAttribute("books", books);

		return "customerBookList";
	}
}