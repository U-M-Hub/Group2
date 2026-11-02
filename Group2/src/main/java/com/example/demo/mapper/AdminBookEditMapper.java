package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Books;

@Mapper
public interface AdminBookEditMapper {
	
	//指定された書籍IDの書籍情報を１件取得する
	
	/*
	 * 多分このSQL文イランかも
	@Select("""
			SELECT
			book_id,
			book_title,
			book_author,
			book_price,
			book_summary,
			book_cover,
			book_genre,
			book_stock,
			book_publisher,
			book_flag
			FROM books
			WHERE book_id = #{bookId}
			""")
	 */
	Books findById(@Param("bookId") Integer BookId);
	
	//書籍情報を更新する
	int update(Books books);
	
}
