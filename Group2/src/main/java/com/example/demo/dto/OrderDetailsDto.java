package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDetailsDto {

	/** 注文明細ID */
	private Integer orderDetailId;

	/** ユーザーID */
	private Integer userId;

	/** ユーザー名 */
	private String userName;	// usersテーブルから取得

	/** 注文番号 */
	private Integer orderNumber;

	/** (注文番号)枝番 */
	private Integer orderBranchNumber;

	/** 書籍ID */
	private Integer bookId;

	/** タイトル */
	private String bookTitle;	// booksテーブルから取得
		
	/** 価格 */
	private Integer orderPrice;
		
	/** 数量 */
	private Integer bookCount;
		
	/** 注文日 */
	private LocalDate orderDate;

}
