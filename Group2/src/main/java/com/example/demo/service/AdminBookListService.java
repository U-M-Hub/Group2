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
	 * 1ページに表示する本の情報を取得するMapperを呼び出すService
	 */
	public List<AdminBookListDto> selectAllBookWithGenre(
									AdminBookListForm adminBookListForm,
									Integer pageCount
									) {
		//ページ番号をもとに、検索結果から何件のデータを除外するか判定
		int offSet = 20 * pageCount - 20;
		
		//検索条件と除外件数をMapperへ渡す
		return adminBookListMapper.selectAllBookWithGenre(adminBookListForm, offSet);
	}
	
	//検索条件を満たす本の件数を取得するMapperを呼び出すService
	public Integer allBookCount(AdminBookListForm adminBookListForm) {
		return adminBookListMapper.allBookCount(adminBookListForm);
	}
	
	//全てのジャンルの情報を取得するMapperを呼び出すService
	public List<Genres> selectAllGenre(){
		return adminBookListMapper.selectAllGenre();
	}
	
	//渡されたページ番号が適切かどうが判断し、不正なら正しい値を返すService
	public Integer checkPageCount(AdminBookListForm adminBookListForm,
								  Integer pageCount) {
		
		//検索条件を満たす本の件数をbookCountへ代入
		Integer bookCount = adminBookListMapper.allBookCount(adminBookListForm);
		
		/*
		 * 件数から、最大ページ数を決定してmaxPageへ代入
		 * (計算結果が0になった場合はMath.maxで1を指定)
		 */
		Integer maxPage = Math.max(1, (int) Math.ceil(bookCount / 20.0));
		
		/*
		 * ページ番号がマイナスなら1を返す
		 * ページ番号が最大ページ数を超えていたら、最大ページ数を返す
		 */
		if(pageCount < 1) {
			return 1; 
		}else if(pageCount > maxPage){
			return maxPage;
		}
		
		return pageCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
