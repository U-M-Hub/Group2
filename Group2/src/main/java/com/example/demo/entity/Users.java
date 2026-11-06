package com.example.demo.entity;

import com.example.demo.security.Role;

import lombok.Data;

@Data
public class Users {

    private Integer user_id;
    
    private String user_name;
    
    private String user_password;
    
    private Role user_role;

}
