package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerBookListDto;
import com.example.demo.entity.Genres;
import com.example.demo.mapper.CustomerBookListMapper;

@Service
public class CustomerBookListService {
	  private final CustomerBookListMapper customerBookListMapper;

	    public CustomerBookListService(
	            CustomerBookListMapper customerBookListMapper) {
	        this.customerBookListMapper = customerBookListMapper;
	    }

	    public List<CustomerBookListDto> findAll(
	            String sortBy,
	            int limit,
	            int offset,
	            String bookTitle,
	            String bookAuthor,
	            String bookPublisher,
	            String genreId) {

	        return customerBookListMapper.findAll(
	                sortBy, limit, offset,
	                bookTitle, bookAuthor, bookPublisher, genreId);
	    }
	    public int countAll(
	            String bookTitle,
	            String bookAuthor,
	            String bookPublisher,
	            String genreId) {

	        return customerBookListMapper.countAll(
	                bookTitle, bookAuthor, bookPublisher, genreId);
	        
	      
	    }
	    public List<Genres> findAllGenres() {
            return customerBookListMapper.findAllGenres();
       }
	    
	    
}
