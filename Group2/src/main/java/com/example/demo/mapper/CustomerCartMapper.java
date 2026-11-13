package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CustomerCartDto;

@Mapper
public interface CustomerCartMapper {

	/* 消費者：カートテーブル一覧取得			*/
	/* ※取得対象：ユーザーIDに紐づいているもの */
    List<CustomerCartDto> listBooksInCartAll(
    		@Param("userID") Integer userId
    		);

	/* 消費者：カートテーブルから書籍データを削除		 */
	/* ※削除対象：ユーザーIDに紐づいている書籍データ	*/
    int deleteBookInCartByBookId(
    		@Param("userID") Integer userId,
            @Param("bookID") Integer bookId
            );

	/* 消費者：カート画面から注文実施		*/
    /* ① 注文履歴テーブルへの登録			*/
    /* ② カートテーブルからの削除含む		*/  
    
    // ① - 1 注文番号を取得
    Integer getNextOrderNumber();

    // ① - 2 注文対象の書籍情報を取得
    List<CustomerCartDto> findOrderBooks(
            @Param("userID") Integer userId
            );

    // ① - 3 注文詳細テーブルへ登録
    int insertOrderDetail(
            @Param("orderNumber") Integer orderNumber,
            @Param("branchNumber") Integer branchNumber,
            @Param("userID") Integer userId,
            @Param("book") CustomerCartDto book
    );    

    // ① - 4 書籍の在庫数を減らす
    int updateBookStock(
            @Param("bookID") Integer bookId,
            @Param("bookCount") Integer bookCount
    );
    
    // ② カートテーブルから注文済み書籍を削除
    int deleteOrderedBooks(
            @Param("userID") Integer userId
    );

}
