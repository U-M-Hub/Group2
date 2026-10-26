package com.example.demo.form.adminBookList;

import lombok.Data;

@Data
public class AdminBookListForm {

	private String name;
	
	private String author;
	
	private String publisher;
	
	private String order;
	
	private Integer genre_id;
	
	private Boolean flag;
}
