package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CustomerBookDetailsDto;
import com.example.demo.dto.CustomerCartDto;

@Mapper
public interface CustomerBookDetailsMapper {

	
	/* 消費者：商品詳細画面表示時にカートテーブルを確認 */
	/* ※ユーザーIDと書籍IDの組み合わせの有無を確認		*/
	Integer selectCartCount(
			@Param("userId") Integer userID,
	        @Param("bookId") Integer bookID
	    );
	
	/* 消費者：書籍IDで取得 */
	CustomerBookDetailsDto getBookDetailsByBookId(int id);	

	/* 消費者：カートテーブルに追加 */
	void insertCustomerCart(CustomerCartDto customerCartDto);	
	
}
