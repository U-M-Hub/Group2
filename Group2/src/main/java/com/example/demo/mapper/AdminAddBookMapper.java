package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Books;
import com.example.demo.entity.Genres;

@Mapper
public interface AdminAddBookMapper {
	
	@Select("""
			SELECT genre_id, genre_name
			FROM genres
			ORDER BY genre_id
			""")
	List<Genres> findAll();
	
	int insert(Books books);

	void save(Books book);

}
