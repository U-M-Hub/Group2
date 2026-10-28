package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerBookListDto;
import com.example.demo.mapper.CustomerBookListMapper;

@Service
public class CustomerBookListService {
	  private final CustomerBookListMapper customerBookListMapper;

	    public CustomerBookListService(
	            CustomerBookListMapper customerBookListMapper) {
	        this.customerBookListMapper = customerBookListMapper;
	    }

	    public List<CustomerBookListDto> findAll(
	            String sortBy, int limit, int offset) {
	        return customerBookListMapper.findAll(sortBy, limit, offset);
	    }
	    public int countAll() {
	        return customerBookListMapper.countAll();
	    }
}
