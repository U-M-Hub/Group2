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


	//文字列フィールドの値から空白を除去するメソッドたち(SQL文で使用)
	public String getNoSpaceName() {

		if(this.name != null) {
			return this.name.replace(" ", "").replace("　", "");
		}
		return this.name;
	}

	public String getNoSpaceAuthor() {

		if(this.author != null) {
			return this.author.replace(" ", "").replace("　", "");
		}
		return this.author;
	}

	public String getNoSpacePublisher() {

		if(this.publisher != null) {
			return this.publisher.replace(" ", "").replace("　", "");
		}
		return this.publisher;
	}
}
