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
import com.example.demo.service.AdminAddBookGenreService;
import com.example.demo.service.AdminAddBookImageService;
import com.example.demo.service.AdminAddBookService;


@Controller
@RequestMapping("/admin/books/")
public class AdminAddBookController {
	
	//書籍を登録するService
	private final AdminAddBookService adminAddBookService;
	//ジャンル一覧を取得するService
	private final AdminAddBookGenreService adminAddBookGenreService;
	//表紙画像一覧を取得するService
	private final AdminAddBookImageService adminAddBookImageService;
	
	//コンストラクタ：必要なServiceを受け取る
	public AdminAddBookController(AdminAddBookService adminAddBookService,
			AdminAddBookGenreService adminAddBookGenreService,
			AdminAddBookImageService adminAddBookImageService) {
		this.adminAddBookService = adminAddBookService;
		this.adminAddBookGenreService = adminAddBookGenreService;
		this.adminAddBookImageService = adminAddBookImageService;
		
	}
	
	
	//GET:新規登録画面を表示する
	@GetMapping("/adminAddBook")
	public String showForm(Model model) throws IOException{
		//入力フォーム用のオブジェクトを作成する
		model.addAttribute("BookForm", new BookForm());
		//ジャンル一覧を取得して画面に表示する」
		model.addAttribute("genres", adminAddBookGenreService.findAllGenres());
		//表紙画面一覧を取得して画面に表示する
		model.addAttribute("images", adminAddBookImageService.findAllImages());
		return "adminAddBook";
	}
	
	
	//POST:入力された書籍情報を受け取る
	@PostMapping("/adminAddBook")
	public String register(@Valid @ModelAttribute("BookForm") BookForm form,
			BindingResult result, Model model) throws IOException {
		//入力チェックでエラーが発生した場合
		if(result.hasErrors()) {
			//画面を再表示するため、ジャンル一覧を取得する
			model.addAttribute("genres", adminAddBookGenreService.findAllGenres());
			//表紙画面一覧も取得する
			model.addAttribute("images", adminAddBookImageService.findAllImages());
			//エラーメッセージとともに登録画面へ戻る
			return "adminAddBook";
		}
		
		//入力内容に問題がなければ書籍を登録する
		adminAddBookService.register(form);
		
		//Serviseを呼び出してDBに登録する処理。登録完了後、登録画面へリダイレクトする
		return "redirect:/admin/books/adminAddBook?success";
	}

}
