package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminHomeMapper {
	
	//登録書籍数を取得
	int countBooks();
	
	//注文件数を取得
	int countOrders();
	
	//在庫切れの書籍数を取得
	int countOutOfStockBooks();
	
	//公開書籍数を取得
	int countPublishedBooks();
	
	//非公開書籍を取得
	int countUnpublishedBooks();
}
