package com.example.demo.entity;


import lombok.Data;

@Data
public class Books {

    private final Integer book_id;
    
    private final String book_title;
    
    private final String book_author;
    
    private final Integer book_price;
    
    private final String book_summary;
    
    private final String book_cover;
    
    private final Integer book_genre;
    
    private final Integer book_stock;
    
    private final String book_publisher;
    
    private final Boolean book_flag;

}
