package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.OrderDetailsDto;

@Mapper
public interface AdminOrderDetailsMapper {

	/* 商品管理者：全件取得 */
	List<OrderDetailsDto> getAllOrderDetails();
	
}
