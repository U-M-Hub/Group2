package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Books;

@Mapper
public interface CustomerHomeMapper {
	
	//新着書籍を5件取得
	List<Books> selectNewBooks();

}
