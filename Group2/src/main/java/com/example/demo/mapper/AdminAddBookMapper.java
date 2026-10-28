package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Books;
import com.example.demo.entity.Genres;

@Mapper
public interface AdminAddBookMapper {
	
	//ジャンル一覧を取得するSQL
	@Select("""
			SELECT genre_id, genre_name
			FROM genres
			ORDER BY genre_id
			""")
	List<Genres> findAll();
	
	//書籍を登録する
	int insert(Books books);

}
