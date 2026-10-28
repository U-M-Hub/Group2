package com.example.demo.dto;

import lombok.Data;

/*
 * 管理者画面の登録商品一覧で表示する本、1件に対する情報を持つDTO
 */
@Data
public class AdminBookListDto {

	//書籍ID
	private Integer book_id;

	//書籍タイトル
	private String book_title;

	//著者
	private String book_author;

	//価格
	private Integer book_price;
	
	//あらすじ
	private String book_summary;

	//表紙
	private String book_cover;
	
	//ジャンルID
	private Integer book_genre;

	//ジャンル名
	private String genre_name;
	
	//在庫数
	private Integer book_stock;

	//出版社
	private String book_publisher;

	//公開状態
	private Boolean book_flag;
}
