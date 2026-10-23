package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.mapper.OrderDetailsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

	// DI
	private final OrderDetailsMapper orderDetailsMapper;
	
	/* 商品管理者：全件取得 */
	public List<OrderDetails> getAllOrderDetails(){
		return orderDetailsMapper.getAllOrderDetails();
	}

	/* 消費者：ユーザーIDで取得 */
	public OrderDetails getOrderDetailsByUserId(int id){
		return orderDetailsMapper.getOrderDetailsByUserId(id);
		
	}
}
