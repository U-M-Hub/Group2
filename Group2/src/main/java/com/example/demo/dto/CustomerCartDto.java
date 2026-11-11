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

	/** 価格（単価） */
	private Integer bookPrice;

	// 価格（単価 × 注文数）
    public Integer getTotalPrice() {
        return bookPrice * bookCount;
    }	

  	// 公開情報フラグ
    private Boolean bookFlag; 

}
