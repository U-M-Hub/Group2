package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminBookListDto;
import com.example.demo.form.AdminBookListForm;
import com.example.demo.mapper.AdminBookListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminBookListService {

	private final AdminBookListMapper adminBookListMapper;
	
	public List<AdminBookListDto> selectAllBookWithGenre(
									AdminBookListForm adminBookListForm,
									Integer pageCount
									) {
		int offSet = 20 * pageCount - 20;
		
		return adminBookListMapper.selectAllBookWithGenre(adminBookListForm, offSet);
	}
	
	public Integer allBookCount() {
		return adminBookListMapper.allBookCount();
	}
}
