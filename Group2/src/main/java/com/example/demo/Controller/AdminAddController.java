package com.example.demo.Controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.BookForm;

@Controller
@RequestMapping("/admin/books/")
public class AdminAddController {
	
	@GetMapping("/new")
	public String showForm(Model model) {
		model.addAttribute("BookForm", new BookForm());
		return "admin/books/new";
	}
	
	@PostMapping("/new")
	public String register(@Valid @ModelAttribute("BookForm") BookForm form,
			BindingResult result) {
		if(result.hasErrors()) {
			return "admin/books/new";
		}
		//Serviseを呼び出してDBに登録する処理
		return "redirect:/admin/books";
	}
	
	

}
