package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Books;
import com.example.demo.mapper.AdminBookEditMapper;

@Service
public class AdminBookEditService {
	
	//書籍編集用Mapper
	private final AdminBookEditMapper adminBookEditMapper;
	
	//コンストラクタ：Mapperを受け取って利用できるようにする
	public AdminBookEditService(AdminBookEditMapper adminBookEditMapper) {
		this.adminBookEditMapper = adminBookEditMapper;
	}
	
	//指定された書籍IDの情報を取得する
	public Books findById(Integer BookId) {
		return adminBookEditMapper.findById(BookId);
	}
	
	//書籍情報を更新する
	//トランザクションにより、DB更新処理をひとまとまりとして扱う
	@Transactional
	public void update(Books books) {
		//指定された書籍情報をDBに保存する
		int updateCount = adminBookEditMapper.update(books);
		//指定したIDの書籍が存在しない場合はエラーにする
		if(updateCount == 0) {
			throw new IllegalArgumentException("更新対象の書籍が見つかりません。書籍ID："
					+ books.getBook_id());
		}
		
	}

}
