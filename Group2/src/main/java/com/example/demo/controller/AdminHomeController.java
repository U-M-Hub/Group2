package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.mapper.AdminHomeMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminHomeController {
	
	private final AdminHomeMapper adminHomeMapper;
	
	@GetMapping("/home")
	public String adminHome(Model model) {
		//登録書籍数
		model.addAttribute("bookCount", adminHomeMapper.countBooks());
		//注文件数
		model.addAttribute("orderCount", adminHomeMapper.countOrders());
		//在庫切れの書籍数
		model.addAttribute("outOfStockCount", adminHomeMapper.countOutOfStockBooks());
		//公開書籍数
		model.addAttribute("publishedBookCount", adminHomeMapper.countPublishedBooks());
		//非公開書籍数
		model.addAttribute("unpublishedBookCount", adminHomeMapper.countUnpublishedBooks());
		return "adminHome";
	}

}
