package com.example.demo.controller;

import java.io.IOException;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Books;
import com.example.demo.form.BookForm;
import com.example.demo.form.adminBookList.AdminBookListForm;
import com.example.demo.service.AdminAddBookGenreService;
import com.example.demo.service.AdminAddBookImageService;
import com.example.demo.service.AdminBookEditService;

@Controller
@RequestMapping("/adminBookEdit")
public class AdminBookEditController {
	
	//書籍情報の取得・更新を担当するSetvice
	private final AdminBookEditService adminBookEditService;
	//ジャンル一覧の取得を担当するService
	private final AdminAddBookGenreService adminAddBookGenreService;
	//表紙画面一覧を取得するService
	private final AdminAddBookImageService adminAddBookImageService;
	
	//コンストラクタ：必要なServiceを受け取る
	public AdminBookEditController(
			AdminBookEditService adminBookEditService,
			AdminAddBookGenreService adminAddBookGenreService,
			AdminAddBookImageService adminAddBookImageService) {
		this.adminBookEditService = adminBookEditService;
		this.adminAddBookGenreService = adminAddBookGenreService;
		this.adminAddBookImageService = adminAddBookImageService;
	}
	
	//編集画面を表示する
	//URL例：/adminBookEdit/5
	@GetMapping("/{bookId}")
	public String showEditForm(@PathVariable Integer bookId,
			//一覧画面に戻るためのページ番号・検索条件を受け取る
			@RequestParam(required = false) Integer pageCount,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String publisher,
			@RequestParam(required = false) Integer genre_id,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) Boolean flag,
			Model model
			) throws IOException {
		
		//指定されたIDの書籍情報をDBから取得する
		Books book = adminBookEditService.findById(bookId);
		
		//指定されたIDの書籍が存在しない場合
		if(book == null) {
			model.addAttribute("errorMessage", "指定された書籍が見つかりません");
			model.addAttribute("bookId", bookId);
			
			/*
			 * 
			//一覧画面に戻るための検索条件を設定
			AdminBookListForm searchForm = new AdminBookListForm();
			
			searchForm.setName(name);
			searchForm.setAuthor(author);
			searchForm.setPublisher(publisher);
			searchForm.setGenre_id(genre_id);
			searchForm.setOrder(order);
			searchForm.setFlag(flag);
			
			model.addAttribute("searchForm", searchForm);
			
			//ページ番号を設定
			model.addAttribute("pageCount",
					pageCount != null && pageCount > 0 ? pageCount : 1);
			 */
			
			return "adminBookEditError";
		}
		
		//DBから取得したBooksをBookFormに詰め替える
		BookForm form = new BookForm();
		form.setBook_id(book.getBook_id());
		form.setBook_title(book.getBook_title());
		form.setBook_author(book.getBook_author());
		form.setBook_price(book.getBook_price());
		form.setBook_summary(book.getBook_summary());
		form.setBook_cover(book.getBook_cover());
		form.setBook_genre(book.getBook_genre());
		form.setBook_stock(book.getBook_stock());
		form.setBook_publisher(book.getBook_publisher());
		form.setBook_flag(book.getBook_flag());
		//編集画面にBookFormを渡す
		model.addAttribute("book", form);
		
		//編集画面のジャンル選択肢を取得する
		model.addAttribute("genres", adminAddBookGenreService.findAllGenres());
		
		//表紙画面一覧を取得してHTMLに渡す
		model.addAttribute("images", adminAddBookImageService.findAllImages());
		
		//一覧画面に戻るための検索条件をFormにまとめる
		AdminBookListForm searchForm = new AdminBookListForm();
		searchForm.setName(name);
		searchForm.setAuthor(author);
		searchForm.setGenre_id(genre_id);
		searchForm.setPublisher(publisher);
		searchForm.setFlag(flag);
		searchForm.setOrder(order);
		
		//一覧画面に戻る際に使用する情報を画面に渡す
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("pageCount", pageCount != null ? pageCount : 1);
		
		//templates/adminBookEdit.htmlを表示する
		return "adminBookEdit";
	}
	
	//編集画面から送信された書籍情報を更新する
	@PostMapping("/{bookId}")
	public String updateBook(
			//URLから更新対象の書籍IDを取得する
			@PathVariable Integer bookId,
			//HTMLのフォームから送られた書籍情報を受け取る
			@Valid@ModelAttribute("book") BookForm form,
			BindingResult result,
			//一覧画面に戻るためのページ番号と検索条件
			@RequestParam(required = false) Integer pageCount,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String publisher,
			@RequestParam(required = false) Integer genre_id,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) Boolean flag,
			Model model,
			//リダイレクト先へ値を引き継ぐためのオブジェクト
			RedirectAttributes redirectAttributes
			) throws IOException{
		
		//URLのIDを更新対象のIDとして設定する
		//hidden項目から送られたIDではなく、URLのIDを使用する
		form.setBook_id(bookId);
		//入力チェックでエラーが発生した場合
		if(result.hasErrors()) {
			//ジャンル一覧を再取得
			model.addAttribute("genres", adminAddBookGenreService.findAllGenres());
			//表紙画像一覧を再取得
			model.addAttribute("images", adminAddBookImageService.findAllImages());
			//一覧画面に戻るための情報を再設定
			AdminBookListForm searchForm = new AdminBookListForm();
			searchForm.setName(name);
			searchForm.setAuthor(author);
			searchForm.setPublisher(publisher);
			searchForm.setGenre_id(genre_id);
			searchForm.setOrder(order);
			searchForm.setFlag(flag);
			
			model.addAttribute("searchForm", searchForm);
			model.addAttribute
			("pageCount", pageCount != null && pageCount > 0 ? pageCount : 1);
			//エラーを表示したまま編集画面に戻る
			return "adminBookEdit";
		}
		//入力内容に問題がなければBookFormからBooksに変換する
		Books book = new Books();
		
		book.setBook_id(bookId);
		book.setBook_title(form.getBook_title());
		book.setBook_author(form.getBook_author());
		book.setBook_price(form.getBook_price());
		book.setBook_summary(form.getBook_summary());
		book.setBook_cover(form.getBook_cover());
		book.setBook_genre(form.getBook_genre());
		book.setBook_stock(form.getBook_stock());
		book.setBook_publisher(form.getBook_publisher());
		book.setBook_flag(form.getBook_flag());
		
		//書籍情報をDBに保存する
		adminBookEditService.update(book);
		//ページ番号が渡されなかった場合は１ページ目に戻す
		int page = (pageCount != null && pageCount > 0)
				? pageCount : 1;
		
		//一覧画面へ戻る際に検索条件を引き継ぐ
		redirectAttributes.addAttribute("name", name);
		redirectAttributes.addAttribute("author", author);
		redirectAttributes.addAttribute("publisher", publisher);
		redirectAttributes.addAttribute("genre_id", genre_id);
		redirectAttributes.addAttribute("order", order);
		redirectAttributes.addAttribute("flag", flag);
		
		//更新後、書籍一覧画面へ戻る
		return "redirect:/adminBookList/" + page;
	}

}
