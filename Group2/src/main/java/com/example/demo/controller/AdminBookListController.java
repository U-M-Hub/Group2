package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.form.adminBookList.AdminBookListForm;
import com.example.demo.service.AdminBookListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminBookListController {
	
	private final AdminBookListService adminBookListService;

	@GetMapping("/adminBookList/{pageCount}")
	public String adminBookList(@PathVariable Integer pageCount,
								AdminBookListForm adminBookListForm,
			                    Model model) {
		
		model.addAttribute("booksWithGenre",
				            adminBookListService.selectAllBookWithGenre(
				            		adminBookListForm,
				            		pageCount
				            		)
				            );
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("bookCount", adminBookListService.allBookCount(adminBookListForm));
		model.addAttribute("genres", adminBookListService.selectAllGenre());
		model.addAttribute("serchForm",adminBookListForm);
		
		return "adminBookList";
	}
}
