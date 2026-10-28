package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.mapper.CustomerOrderDetailsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerOrderDetailsService {

	// DI
	private final CustomerOrderDetailsMapper customerOrderDetailsMapper;
	
	/* 消費者：ユーザーIDで取得 					*/
	/* データベースの参照専用処理（データ更新なし） */ 
	@Transactional(readOnly = true)
	public List<OrderDetailsDto> getOrderDetailsByUserId(int id){
		return customerOrderDetailsMapper.getOrderDetailsByUserId(id);
		
	}
	
}
