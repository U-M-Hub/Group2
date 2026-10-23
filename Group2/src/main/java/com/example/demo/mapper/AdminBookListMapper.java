package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.AdminBookListDto;
import com.example.demo.form.AdminBookListForm;

@Mapper
public interface AdminBookListMapper {

	
	/*全ての本の情報を取得し、
	 * genresテーブルから本に対応するgenre_nameをDtoに取り込むMapper
	 */
	List<AdminBookListDto> selectAllBookWithGenre(
							AdminBookListForm adminBookListForm,
							Integer pageCount
							);
	
	Integer allBookCount();
}
