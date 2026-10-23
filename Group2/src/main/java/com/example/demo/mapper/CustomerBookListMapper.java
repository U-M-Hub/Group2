package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Books;

@Mapper
public interface CustomerBookListMapper {

    List<Books> findAll();

}
