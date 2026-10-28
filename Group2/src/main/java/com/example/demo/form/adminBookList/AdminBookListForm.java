package com.example.demo.form.adminBookList;

import lombok.Data;

/*
 * 検索条件の情報を全て格納したForm
 */
@Data
public class AdminBookListForm {

	//書籍タイトル
	private String name;
	
	//著者
	private String author;
	
	//出版社
	private String publisher;
	
	//並び順
	private String order;
	
	//ジャンルID
	private Integer genre_id;
	
	//公開状態
	private Boolean flag;
}
