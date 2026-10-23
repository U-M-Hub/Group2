package com.example.demo.dto;

import lombok.Data;

@Data
public class AdminBookListDto {

	private Integer book_id;

	private String book_title;

	private String book_author;

	private Integer book_price;
	
	private String book_summary;

	private String book_cover;
	
	private Integer book_genre;

	private String genre_name;
	
	private Integer book_stock;

	private String book_publisher;

	private Boolean book_flag;
}
