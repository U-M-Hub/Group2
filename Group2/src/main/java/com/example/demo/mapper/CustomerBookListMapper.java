package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CustomerBookListDto;
import com.example.demo.entity.Genres;

@Mapper
public interface CustomerBookListMapper {

    List<CustomerBookListDto> findAll(
            @Param("sortBy") String sortBy,
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("bookTitle") String bookTitle,
            @Param("bookAuthor") String bookAuthor,
            @Param("bookPublisher") String bookPublisher,
            @Param("genreId") String genreId
    );

    int countAll(
            @Param("bookTitle") String bookTitle,
            @Param("bookAuthor") String bookAuthor,
            @Param("bookPublisher") String bookPublisher,
            @Param("genreId") String genreId
    );
    List<Genres> findAllGenres();
}