package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.CustomerCartDto;
import com.example.demo.security.LoginUser;
import com.example.demo.service.CustomerCartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerCartController {

	// この場合、に表示するURLは
	//   @RequestMapping("/customer")
	//   @GetMapping("/cart")
	// の為、URLは http://localhost:8080/customer/cart
	// URL構成が決まったら該当する内容に変更する必要あり。

    private final CustomerCartService customerCartService;

    // 一覧画面表示
    @GetMapping("/cart")
    public String showCartByUserId(
    		@AuthenticationPrincipal LoginUser loginUser,
    		Model model
    		) {

    	// ログインIDの取得
    	Integer loginId	= loginUser.getUserId();
    	
		List<CustomerCartDto> cartList =
	            customerCartService.listBooksInCartAll(loginId);

		model.addAttribute("message", "カート一覧");
	    model.addAttribute("cartList", cartList);
	    model.addAttribute("cartListCount", cartList.size());        
        return "customerCart";
    }
	
    // 書籍削除
    @PostMapping("/cart/delete")
    public String deleteCartByBookId(
    		@AuthenticationPrincipal LoginUser loginUser,
            @RequestParam("bookID") Integer selectBookId) {

        customerCartService.deleteBookInCartByBookId(
        		loginUser.getUserId(),
        		selectBookId
        		);

        return "redirect:/customer/cart";

    }

    // 注文履歴テーブルへの登録
    // ※カートテーブルからの削除含む
    @PostMapping("/cart/confirm")
    public String insertCustomerOrderDetails(
    		@AuthenticationPrincipal LoginUser loginUser,
            RedirectAttributes redirectAttributes
            ) {

    	try {
    		
	        // 注文確定処理
    		// ※戻り値：注文番号
    		Integer orderNumber =
    				customerCartService.confirmCustomerCartOrders(loginUser.getUserId());

    		// 取得した注文番号を画面表示用に6桁固定（0補填)
    		String orderNumberStr = String.format("%06d", orderNumber);
    		
	    	// 注文完了メッセージを設定
	    	redirectAttributes.addFlashAttribute(
	    	    "completeMessage",
	    	    "注文が完了しました。（注文番号：" + orderNumberStr + "）"
	    	);    	

    	} catch (IllegalStateException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    e.getMessage()
            );
            
        	// カート画面を再表示する
            return "redirect:/customer/cart";
        }
    	
    	// 注文履歴画面へ遷移
        return "redirect:/customer/orders";

    }
    
}
