package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Order_details {

    private final Integer order_detail_id;
    
    private final Integer user_id;
    
    private final Integer order_number;
    
    private final Integer order_branch_number;
    
    private final Integer book_id;
    
    private final Integer order_price;
    
    private final Integer book_count;
    	
    private final LocalDate order_date;

}
