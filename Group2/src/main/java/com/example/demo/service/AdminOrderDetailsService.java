package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.mapper.AdminOrderDetailsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderDetailsService {

	// DI
	private final AdminOrderDetailsMapper adminrderDetailsMapper;

	/* 商品管理者：全件取得 						*/
	/* データベースの参照専用処理（データ更新なし） */ 
	@Transactional(readOnly = true)
	public List<OrderDetailsDto> getAllOrderDetails(){
		return adminrderDetailsMapper.getAllOrderDetails();
	}
	
}
