package com.example.demo.entity;

import lombok.Data;

@Data
public class Cart {

    private final Integer user_id;
    
    private final Integer book_id;
    
    private final Integer book_count;

}
