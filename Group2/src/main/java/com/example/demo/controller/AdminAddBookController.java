package com.example.demo.controller;

import java.io.IOException;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.BookForm;
import com.example.demo.mapper.AdminAddBookMapper;
import com.example.demo.service.AdminAddBookImageService;
import com.example.demo.service.AdminAddBookService;


@Controller
@RequestMapping("/admin/books/")
public class AdminAddBookController {
	
	private final AdminAddBookService adminAddBookService;
	private final AdminAddBookMapper adminAddBookMapper;
	private final AdminAddBookImageService adminAddBookImageService;
	
	public AdminAddBookController(AdminAddBookService adminAddBookService,
			AdminAddBookMapper adminAddBookMapper,
			AdminAddBookImageService adminAddBookImageService) {
		this.adminAddBookService = adminAddBookService;
		this.adminAddBookMapper = adminAddBookMapper;
		this.adminAddBookImageService = adminAddBookImageService;
		
	}
	
	@GetMapping("/adminAddBook")
	public String showForm(Model model) throws IOException{
		model.addAttribute("BookForm", new BookForm());
		model.addAttribute("genres", adminAddBookMapper.findAll());
		model.addAttribute("images", adminAddBookImageService.findAllImages());
		return "adminAddBook";
	}
	
	@PostMapping("/adminAddBook")
	public String register(@Valid @ModelAttribute("BookForm") BookForm form,
			BindingResult result, Model model) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute("genres", adminAddBookMapper.findAll());
			model.addAttribute("images", adminAddBookImageService.findAllImages());
			return "adminAddBook";
		}
		
		adminAddBookService.register(form);
		
		//Serviseを呼び出してDBに登録する処理
		return "redirect:/admin/books/adminAddBook?success";
	}

}
