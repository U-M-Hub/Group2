package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    private Integer book_id;
    
    private String book_title;
    
    private String book_author;
    
    private Integer book_price;
    
    private String book_summary;
    
    private String book_cover;
    
    private Integer book_genre;
    
    private Integer book_stock;
    
    private String book_publisher;
    
    private Boolean book_flag;

}
