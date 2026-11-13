package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.CustomerBookDetailsDto;
import com.example.demo.dto.CustomerCartDto;
import com.example.demo.form.CustomerBookDetailsForm;
import com.example.demo.security.LoginUser;
import com.example.demo.service.CustomerBookDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerBookDetailsController {

	// この場合、に表示するURLは
	//   @RequestMapping("/customer")
	//   @GetMapping("/books/{bookid}")
	// の為、URLは http://localhost:8080/customer/books/bookid
	// ※bookidは数値
	
	// DI
	private final CustomerBookDetailsService customerBookDetailsService;

	/* 消費者：書籍IDで取得 */
	/* 注文履歴画面から遷移時に fromパラメータで判断出来るようにする */
	/* ※注文履歴画面から遷移時に fromパラメータで判断出来るようにする */
	@GetMapping("/books/{bookId}")
	public String showCustomerBookDetailsByBookId(
				@AuthenticationPrincipal LoginUser loginUser,
				@PathVariable("bookId") int selectBookId,
				@RequestParam(value = "from", required = false) String fromPage,
				Model model
			) {

		// 書籍情報を取得
		CustomerBookDetailsDto dtoBookDetail =
				customerBookDetailsService.getBookDetailsByBookId(selectBookId);
		
		// 書籍情報が存在しない場合
		// ※存在しない書籍IDがセットされた場合の対応
		if (dtoBookDetail == null) {
			model.addAttribute(
	                "errorMessage",
	                "指定された書籍は存在しません。"
					);

	        return "error/404";
	    }
		
		// カートテーブルを確認
		// ※該当ユーザーID(ログインID)/書籍IDの組み合わせ有無を確認
		// ログインIDの取得
		Integer loginId	= loginUser.getUserId();

		// cartテーブルを検索してcountを取得
		int count =
				customerBookDetailsService.getCartCount(loginId, selectBookId);

		// 注文数フォームを作成
		// ※Flash Attributeで引き継いだフォーム情報が存在しない場合のみ、新規作成する。
		//   Validationエラー後のリダイレクト時は、引き継いだ入力内容をそのまま使用する。
		if (!model.containsAttribute("BookCount")) {
		    CustomerBookDetailsForm cbdForm = new CustomerBookDetailsForm();
		    model.addAttribute("BookCount", cbdForm);
		}
		
		model.addAttribute("message", "商品詳細");
		model.addAttribute("bookDetails", dtoBookDetail);
		model.addAttribute("from", fromPage);
		model.addAttribute("numberOfMatches", count);
		
		return "/customerBookDetails";	// 使用html: customerBookDetails.html
	}	

	/* 消費者：カートテーブルに対する追加処理 					*/
	/* ※@PathVariable("id")：				　					*/
	/*     URLからidを取得										*/
	/*   @ModelAttribute("BookCount")：							*/
	/*     HTMLから送信された入力内容をJavaのオブジェクトに格納	*/
	/*　   →カッコ内の値は th:object でセットしている値		*/
	@PostMapping("/cart")
	public String addCustomerCart(
				@AuthenticationPrincipal LoginUser loginUser,
				@RequestParam("bookID") Integer selectBookId,
				@Validated
				@ModelAttribute("BookCount") CustomerBookDetailsForm cbdForm,
				BindingResult bindingResult,
				RedirectAttributes redirectAttributes
			) {

		// 注文数が未入力の場合
		if (bindingResult.hasErrors()) {

			// 入力内容を引き継ぐ
		    redirectAttributes.addFlashAttribute(
		            "BookCount", cbdForm);

		    // Validationエラー情報を引き継ぐ
		    redirectAttributes.addFlashAttribute(
		            "org.springframework.validation.BindingResult.BookCount",
		            bindingResult);

		    return "redirect:/customer/books/" + selectBookId;
		    
		}
		
		// DBからDTOを再取得
	    CustomerBookDetailsDto cbdDto =
	    		customerBookDetailsService.getBookDetailsByBookId(selectBookId);

	    // 登録用DTOを作成
	    CustomerCartDto insertCcDto = new CustomerCartDto();

		// ※該当ユーザーID(ログインID)を登録用DTOにセット
		// ログインIDの取得
		Integer loginId	= loginUser.getUserId();
	    insertCcDto.setUserId(loginId);
	    
	    // DBから再取得したデータを登録用DTOにセット
	    insertCcDto.setBookId(cbdDto.getBookId());			// 書籍ID
	    insertCcDto.setBookTitle(cbdDto.getBookTitle());	// タイトル
	    
	    // Formから取得した値を登録用DTOにセット
	    insertCcDto.setBookCount(cbdForm.getBookCount());	// 注文数

		 // 次にServiceへ登録用DTOを渡す
	    customerBookDetailsService.insertCustomerCart(insertCcDto);
	    
	    // 登録処理後にカート画面表示のSELECT処理を実行させたい為
	    //   return customerCart（htmlファイル名）
	    // ではなく、
	    //   return "redirect:/customer/cart" （カート画面のURL）
	    // としている。
	    return "redirect:/customer/cart";

	}	
	
}
