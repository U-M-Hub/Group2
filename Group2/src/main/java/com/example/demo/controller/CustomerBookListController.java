package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CustomerBookListDto;
import com.example.demo.entity.Genres;
import com.example.demo.service.CustomerBookListService;

@Controller
public class CustomerBookListController {
	private final CustomerBookListService customerBookListService;

	public CustomerBookListController(
			CustomerBookListService customerBookListService) {
		this.customerBookListService = customerBookListService;
	}

	@GetMapping("/customer/books")
	public String showBookList(
	        @RequestParam(defaultValue = "bookIdDesc") String sortBy,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "") String bookTitle,
	        @RequestParam(defaultValue = "") String bookAuthor,
	        @RequestParam(defaultValue = "") String bookPublisher,
	        @RequestParam(defaultValue = "") String genreId,
	        Model model) {

	    // 1ページに表示する書籍数
	    int pageSize = 20;

	    // 公開中の書籍の総件数
	    int totalCount = customerBookListService.countAll(
	            bookTitle, bookAuthor, bookPublisher, genreId);
	    // 必要なページ数
	    int totalPages = Math.max(
	            1, (int) Math.ceil((double) totalCount / pageSize));

	    // ページ番号を補正
	    int currentPage = Math.max(1, Math.min(page, totalPages));

	    // データの取得開始位置
	    int offset = (currentPage - 1) * pageSize;

	    // 書籍一覧を取得
	    List<CustomerBookListDto> books =
	            customerBookListService.findAll(
	                    sortBy, pageSize, offset,
	                    bookTitle, bookAuthor, bookPublisher, genreId);

	    // 一覧を画面に渡す
	    model.addAttribute("books", books);
	    model.addAttribute("sortBy", sortBy);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("hasPrevious", currentPage > 1);
	    model.addAttribute("hasNext", currentPage < totalPages);
	    // ジャンル一覧を取得
	    List<Genres> genres = customerBookListService.findAllGenres();

	    // HTMLにジャンル一覧を渡す
	    model.addAttribute("genres", genres);
	    
	    // 検索フォームの入力値を画面に渡す
	    model.addAttribute("bookTitle", bookTitle);
	    model.addAttribute("bookAuthor", bookAuthor);
	    model.addAttribute("bookPublisher", bookPublisher);
	    model.addAttribute("genreId", genreId);

	    return "customerBookList";
	}
}