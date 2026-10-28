package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CustomerBookListDto;

@Mapper
public interface CustomerBookListMapper {

	List<CustomerBookListDto> findAll(
	        @Param("sortBy") String sortBy,
	        @Param("limit") int limit,
	        @Param("offset") int offset);
	int countAll();
}
