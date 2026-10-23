package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.OrderDetails;

@Mapper
public interface OrderDetailsMapper {

	/* 商品管理者：全件取得 */
	List<OrderDetails> getAllOrderDetails();
	
	/* 消費者：ユーザーIDで取得 */
	OrderDetails getOrderDetailsByUserId(int id);
	
}
