package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Books;
import com.example.demo.mapper.CustomerBookListMapper;

@Service
public class CustomerBookListService {
	  private final CustomerBookListMapper customerBookListMapper;

	    public CustomerBookListService(
	            CustomerBookListMapper customerBookListMapper) {
	        this.customerBookListMapper = customerBookListMapper;
	    }

	    public List<Books> findAll() {
	        return customerBookListMapper.findAll();
	    }
}
