package com.example.demo.service;

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
    public List<CustomerCartDto> listBooksInCartAll() {

        return customerCartMapper.listBooksInCartAll();
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
	
}
