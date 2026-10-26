package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminBookListDto;
import com.example.demo.entity.Genres;
import com.example.demo.form.adminBookList.AdminBookListForm;
import com.example.demo.mapper.AdminBookListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminBookListService {

	private final AdminBookListMapper adminBookListMapper;
	
	
	/*
	 * 引数で渡されたページ番号をもとに、
	 * 1ページに表示する本の情報を取得してListで返すService
	 */
	public List<AdminBookListDto> selectAllBookWithGenre(
									AdminBookListForm adminBookListForm,
									Integer pageCount
									) {
		int offSet = 20 * pageCount - 20;
		
		return adminBookListMapper.selectAllBookWithGenre(adminBookListForm, offSet);
	}
	
	//検索条件にあてはまる本の件数を取得するService
	public Integer allBookCount(AdminBookListForm adminBookListForm) {
		return adminBookListMapper.allBookCount(adminBookListForm);
	}
	
	//全てのジャンルの情報を取得するMapper
	public List<Genres> selectAllGenre(){
		return adminBookListMapper.selectAllGenre();
	}
}
