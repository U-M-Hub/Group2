package com.example.demo.entity;

import lombok.Data;

@Data
public class Users {

    private final Integer user_id;
    
    private final String user_name;
    
    private final String user_password;
    
    private final String user_role;

}
