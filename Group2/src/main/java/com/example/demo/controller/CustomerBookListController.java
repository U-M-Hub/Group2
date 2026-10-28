package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CustomerBookListDto;
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
	        Model model) {

	    // 1ページに表示する書籍数
	    int pageSize = 20;

	    // 公開中の書籍の総件数
	    int totalCount = customerBookListService.countAll();

	    // 必要なページ数（最低1ページ）
	    int totalPages = Math.max(
	            1, (int) Math.ceil((double) totalCount / pageSize));

	    // ページ番号が範囲外なら補正する
	    int currentPage = Math.max(1, Math.min(page, totalPages));

	    // SQLで取得を開始する位置
	    int offset = (currentPage - 1) * pageSize;

	    // 指定されたページの書籍を取得
	    List<CustomerBookListDto> books =
	            customerBookListService.findAll(sortBy, pageSize, offset);

	    // HTMLに渡すデータ
	    model.addAttribute("books", books);
	    model.addAttribute("sortBy", sortBy);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("hasPrevious", currentPage > 1);
	    model.addAttribute("hasNext", currentPage < totalPages);

	    return "customerBookList";
	}
}