package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerBookListDto {

    private Integer book_id;
    private String book_cover;
    private String book_title;
    private String book_author;
    private String genre_name;
    private String book_publisher;
    private Integer book_price;

}