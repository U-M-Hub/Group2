package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerCartDto {

	/** ユーザーID */
	private Integer userId;

	/** 書籍ID */
	private Integer bookId;
	
	/** 本のタイトル */
	private String bookTitle;
	
	/** 注文数 */
	private Integer bookCount;
	
}
