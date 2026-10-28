package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Genres;
import com.example.demo.mapper.AdminAddBookMapper;

@Service
public class AdminAddBookGenreService {
	
	private final AdminAddBookMapper adminAddBookMapper;
	
	//コンストラクタ
	public AdminAddBookGenreService(AdminAddBookMapper adminAddBookMapper) {
		this.adminAddBookMapper = adminAddBookMapper;
	}
	
	//ジャンル一覧を取得する
	public List<Genres> findAllGenres(){
		//Mapperを通してDBからジャンル一覧を取得する
		return adminAddBookMapper.findAll();
	}

}
