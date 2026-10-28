package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.OrderDetailsDto;

@Mapper
public interface CustomerOrderDetailsMapper {

	/* 消費者：ユーザーIDで取得 */
	List<OrderDetailsDto> getOrderDetailsByUserId(int id);	
	
}
