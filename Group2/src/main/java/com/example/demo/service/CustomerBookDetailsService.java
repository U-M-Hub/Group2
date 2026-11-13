package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CustomerBookDetailsDto;
import com.example.demo.dto.CustomerCartDto;
import com.example.demo.mapper.CustomerBookDetailsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerBookDetailsService {

	// DI
	private final CustomerBookDetailsMapper customerBookDetailsMapper;

	/* 消費者：商品詳細画面表示時にカートテーブルを確認 */
	/* ※ユーザーIDと書籍IDの組み合わせの有無を確認		*/
	public int getCartCount(Integer userId, Integer bookId) {

        // 取得した値を返す
		return customerBookDetailsMapper
	            .selectCartCount(userId, bookId);
	}	
	
	/* 消費者：書籍IDで取得 						*/
	/* データベースの参照専用処理（データ更新なし） */ 
	@Transactional(readOnly = true)
	public CustomerBookDetailsDto getBookDetailsByBookId(int id){
		return customerBookDetailsMapper.getBookDetailsByBookId(id);
		
	}

	/* 消費者：[カートへ追加]ボタン押下時に使用	*/
	/* データベースの追加専用処理				*/ 
	public void insertCustomerCart(CustomerCartDto customerCartDto) {

        // カートテーブルへ登録
        customerBookDetailsMapper.insertCustomerCart(customerCartDto);

    }
	
}
