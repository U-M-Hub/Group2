package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerBookDetailsDto {

	/** 書籍ID */
	private Integer bookId;
	
	/** 本のタイトル */
	private String bookTitle;
	
	/** 著者名 */
	private String bookAuthor;
	
	/** 価格 */
	private Integer bookPrice;

	/** あらすじ */
	private String bookSummary;

	/** 表紙(画像) */
	private String bookCover;

	/** ジャンル名 */
	private String genreName;		// genresテーブルから取得

	/** 在庫数 */
	private Integer bookStock;

	/** 出版社 */
	private String bookPublisher;
	
	/** 公開情報 */
	private Boolean bookFlag;

}
