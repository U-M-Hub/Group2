package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
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

	/*
	 * 引数について
	 * adminBookListForm：検索条件の情報を格納しているForm
	 * pageCount：ページ番号
	 */
	@GetMapping("/adminBookList/{pageCount}")
	public String adminBookList(@PathVariable Integer pageCount,
								AdminBookListForm adminBookListForm,
			                    Model model) {
		
		///＝＝＝検索条件の文字列を、空白を除去した形に加工する部分＝＝＝
		
		//加工前の状態を、viewConditionに移して保持しておく
		AdminBookListForm viewCondition = new AdminBookListForm();
		BeanUtils.copyProperties(adminBookListForm, viewCondition);
		
		//adminBookListForm無いの文字列の空白を除去
		adminBookListService.createSearchCondition(adminBookListForm);
		
		//＝＝＝＝＝＝＝＝＝＝＝ここまで＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
		
		//checkPageCount()で、ページ番号が不正なものでないかチェック
		pageCount = adminBookListService.checkPageCount(adminBookListForm, pageCount);
		
		/*
		 * 検索条件とページ番号をもとに取得した、
		 * 今回のページで表示する20件の書籍データをmodelに格納
		 */
		model.addAttribute("booksWithGenre",
				            adminBookListService.selectAllBookWithGenre(
				            		adminBookListForm,
				            		pageCount
				            		)
				            );
		//ページ番号をmodelに格納
		model.addAttribute("pageCount",pageCount);
		//検索条件を満たす書籍の件数をmodelに格納
		model.addAttribute("bookCount", adminBookListService.allBookCount(adminBookListForm));
		//全てのジャンル情報をmodelに格納
		model.addAttribute("genres", adminBookListService.selectAllGenre());
		//検索条件を持ったformをmodelに格納
		model.addAttribute("searchForm",viewCondition);
		
		return "adminBookList";
	}
}
