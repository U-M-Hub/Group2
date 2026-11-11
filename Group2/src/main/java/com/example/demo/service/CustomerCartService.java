package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CustomerCartDto;
import com.example.demo.mapper.CustomerCartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCartService {

	// DI
    private final CustomerCartMapper customerCartMapper;

	/* 消費者：カートテーブル一覧取得			*/
	/* ※取得対象：ユーザーIDに紐づいているもの */
    @Transactional(readOnly = true)
    public List<CustomerCartDto> listBooksInCartAll(
    			Integer selectUserId
    		) {

        return customerCartMapper.listBooksInCartAll(selectUserId);
 
    }

    
	/* 消費者：カートテーブルから書籍データを削除		 */
	/* ※削除対象：ユーザーIDに紐づいている書籍データ	*/
    @Transactional
    public int deleteBookInCartByBookId(
            		Integer selectUserId,
            		Integer selectBookId
    			) {

    	return customerCartMapper.deleteBookInCartByBookId(
    								selectUserId,
    								selectBookId
    							  );

    }

	/* 消費者：カート画面から注文実施		*/
    /* ① 注文履歴テーブルへの登録			*/
    /* ② カートテーブルからの削除含む		*/  
    @Transactional
    public int confirmCustomerCartOrders(
            Integer userId
            ) {

        // ① - 1 注文番号を取得
        Integer orderNumber =
                customerCartMapper.getNextOrderNumber();

        // ① - 2 カートテーブルから注文対象データを取得
        List<CustomerCartDto> bookList =
                customerCartMapper.findOrderBooks(userId);

        // カートが空の場合は処理を中断
        if (bookList.isEmpty()) {
        	throw new IllegalStateException(
        			"[Err]カートに商品はありません。"
        			);
        }
        
	    // ① - 3 注文詳細を注文履歴テーブルに登録
        int branchNumber = 1;
	
	    for (CustomerCartDto book : bookList) {
	
	    	customerCartMapper.insertOrderDetail(
	                    orderNumber,
	                    branchNumber,
	                    userId,
	                    book
	            );
	
	            branchNumber++;
	        }        
        

	    // ① - 4 書籍の在庫数を減らす
	    //        このタイミングで公開情報フラグも確認   
	    List<String> shortageBooks = new ArrayList<>();	// 在庫確認用
	    List<String> invalidBooks = new ArrayList<>();	// 公開情報フラグ確認用

	    for (CustomerCartDto book : bookList) {

	    	// 公開情報フラグがfalseの場合
	        if (Boolean.FALSE.equals(book.getBookFlag())) {
	            invalidBooks.add(book.getBookTitle());
	            continue;
	        }

	        // 在庫数を減らす
	        int result = customerCartMapper.updateBookStock(
	                book.getBookId(),
	                book.getBookCount()
	        );

	        // 在庫不足の書籍をリストに追加
	        // ※条件として、公開情報フラグが True の場合とする。
	        //   (公開情報フラグが False の場合は先に処理済の為)
	        if (result == 0 && Boolean.TRUE.equals(book.getBookFlag())) {
	            shortageBooks.add(book.getBookTitle());
	        }
	    }

	    // エラーメッセージを格納
	    // ※new StringBuilder()：空の文字列入れ物を作る
	    StringBuilder errorMessage = new StringBuilder();

	    // 注文不可の書籍（公開情報フラグ：false）が存在する場合
	    if (!invalidBooks.isEmpty()) {

	    	// append()：文字列を追加
	        errorMessage.append("以下の書籍は注文できません。\n");
	        errorMessage.append(String.join("\n", invalidBooks));
	    }

	    // 在庫不足の書籍が存在する場合
	    if (!shortageBooks.isEmpty()) {

	        if (errorMessage.length() > 0) {
	            errorMessage.append("\n\n");
	        }

	        errorMessage.append("以下の書籍は在庫が不足しています。\n");
	        errorMessage.append(String.join("\n", shortageBooks));
	    }

	    // エラーが1つでもあれば例外を発生させる
	    // ※StringBuilder のままではなく、通常の String に変換してから
	    //   例外メッセージとして渡す
	    if (errorMessage.length() > 0) {
	        throw new IllegalStateException(errorMessage.toString());
	    }	    
	    
        // ② カートテーブルから削除
        customerCartMapper.deleteOrderedBooks(userId);

        // 注文番号を返す
        return orderNumber;
        
    }    
    
}
