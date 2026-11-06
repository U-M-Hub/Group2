package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CustomerBookDetailsDto;
import com.example.demo.dto.CustomerCartDto;
import com.example.demo.form.CustomerBookDetailsForm;
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
			@PathVariable("bookId") int selectBookId,
			@RequestParam(value = "from", required = false) String fromPage,
			Model model) {

		
		// カートテーブルを確認
		// ※該当ユーザーID/書籍IDの組み合わせ有無を確認
		// ユーザーIDは「1」固定
		Integer userId = 1;

		// cartテーブルを検索してcountを取得
		int count =
				customerBookDetailsService.getCartCount(userId, selectBookId);

		// 注文数フォームを作成
		CustomerBookDetailsForm cbdForm = new CustomerBookDetailsForm();
		
		model.addAttribute("message", "商品詳細");
		model.addAttribute("bookDetails", customerBookDetailsService.getBookDetailsByBookId(selectBookId));
		model.addAttribute("BookCount", cbdForm);
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
			@RequestParam("bookID") Integer selectBookId,
	        @ModelAttribute("BookCount") CustomerBookDetailsForm cbdForm) {

		// DBからDTOを再取得
	    CustomerBookDetailsDto cbdDto =
	    		customerBookDetailsService.getBookDetailsByBookId(selectBookId);

	    // 登録用DTOを作成
	    CustomerCartDto insertCcDto = new CustomerCartDto();

		// ユーザーID を登録用DTOにセット
	    // ※現時点では「1」固定
	    insertCcDto.setUserId(1);
	    
	    // DBから再取得したデータを登録用DTOにセット
	    insertCcDto.setBookId(cbdDto.getBookId());			// 書籍ID
	    insertCcDto.setBookTitle(cbdDto.getBookTitle());	// タイトル
	    
	    // Formから取得した値を登録用DTOにセット
	    insertCcDto.setBookCount(cbdForm.getBookCount());	// 注文数

		 // 次にServiceへ登録用DTOを渡す
	    customerBookDetailsService.insertCustomerCart(insertCcDto);
	    
		// customerCart.html が作成出来次第、変更すること
//	    return "/customerCart";
	    return "/customerOrderDetails";
	}	
	
	
	
	
	
}
