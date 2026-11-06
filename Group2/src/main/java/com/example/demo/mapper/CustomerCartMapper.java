package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CustomerCartDto;

@Mapper
public interface CustomerCartMapper {

	/* 消費者：カートテーブル一覧取得			*/
	/* ※取得対象：ユーザーIDに紐づいているもの */
    List<CustomerCartDto> listBooksInCartAll();

	/* 消費者：カートテーブルから書籍データを削除		 */
	/* ※削除対象：ユーザーIDに紐づいている書籍データ	*/
    int deleteBookInCartByBookId(
    		@Param("userID") Integer userId,
            @Param("bookID") Integer bookId
            );

}
