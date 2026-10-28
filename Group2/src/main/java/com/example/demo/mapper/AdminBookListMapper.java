package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.AdminBookListDto;
import com.example.demo.entity.Genres;
import com.example.demo.form.adminBookList.AdminBookListForm;

@Mapper
public interface AdminBookListMapper {

	
	/*
	 * 検索条件にあてはまる全ての本の情報を取得し、
	 * offSetをもとに検索結果を一部除外。
	 * ジャンルIDとジャンル名を結合して、一覧をListで返すMapper
	 */
	List<AdminBookListDto> selectAllBookWithGenre(
							@Param("form") AdminBookListForm adminBookListForm,
							@Param("offSet") Integer offSet
							);
	
	//検索条件にあてはまる本の件数を取得するMapper
	Integer allBookCount(@Param("form") AdminBookListForm adminBookListForm);
	
	//全てのジャンルの情報を取得するMapper
	List<Genres> selectAllGenre();
}
