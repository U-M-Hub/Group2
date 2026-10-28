package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Order_details {

    private Integer order_detail_id;
    
    private Integer user_id;
    
    private Integer order_number;
    
    private Integer order_branch_number;
    
    private Integer book_id;
    
    private Integer order_price;
    
    private Integer book_count;
    	
    private LocalDate order_date;

}
