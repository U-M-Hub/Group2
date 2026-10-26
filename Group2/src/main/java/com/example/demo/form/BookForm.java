package com.example.demo.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
	
	@NotBlank(message = "　※書籍名を入力してください")
	@Size(max = 255, message = "　※書籍名は255文字以内で入力してください")
	private String book_title;
	
	@NotBlank(message = "　※著者名を入力してください")
	@Size(max = 255, message = "　※著者名は255文字以内で入力してください")
	private String book_author;
	
	@NotNull(message = "　※価格を入力してください")
	@Min(value = 1, message = "　※価格は1以上で入力してください")
	private Integer book_price;
	
	//@NotBlank(message = "あらすじを入力してください")
	private String book_summary;
	
	private String book_cover;
	
	@NotNull(message = "　※ジャンルを選択してください")
	private Integer book_genre;
	
	@NotNull(message = "　※在庫数を入力してください")
	@PositiveOrZero(message = "　※在庫数は0以上で入力してください")
	private Integer book_stock;
	
	@NotBlank(message = "　※出版社を入力してください")
	@Size(max = 255, message = "　※書籍名は255文字以内で入力してください")
	private String book_publisher;
	
	private Boolean book_flag = false;
	

}
