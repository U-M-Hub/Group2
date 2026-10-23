package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.form.BookForm;
import com.example.demo.repositry.BookMapper;

@Service
public class BookService {
	
    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void register(BookForm form) {
        Book book = new Book();

        book.setBookTitle(form.getBookTitle());
        book.setBookAuthor(form.getBookAuthor());
        book.setBookPrice(form.getBookPrice());
        book.setStock(form.getBookStock());

        bookMapper.save(book);
    }
}

