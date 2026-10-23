package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
    private Integer bookId;

    private String bookTitle;

    private String bookAuthor;
    
    private Integer bookPrice;
    
    private String bookSummary;
    
    private String bookCover;
    
    private Integer bookGenre;

    private Integer stock;
    
    private String bookPublisher;
    
    private Boolean bookFlag;

	 
}
