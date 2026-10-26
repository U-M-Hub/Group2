package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Books;
import com.example.demo.form.BookForm;
import com.example.demo.mapper.AdminAddBookMapper;


@Service
public class AdminAddBookService {
	
	private final AdminAddBookMapper adminAddBookMapper;

    public AdminAddBookService(AdminAddBookMapper adminAddBookMapper) {
        this.adminAddBookMapper = adminAddBookMapper;
    }

    @Transactional
    public void register(BookForm form) {
        Books book = new Books();
        String summary = form.getBook_summary();
        if(summary == null || summary.isBlank()) {
        	summary = "未登録"; 
        }
        String cover= form.getBook_cover();
        if(cover == null || cover.isBlank()) {
        	cover = "Noimage.png";
        }

        book.setBook_title(form.getBook_title());
        book.setBook_author(form.getBook_author());
        book.setBook_price(form.getBook_price());
        book.setBook_summary(summary);
        book.setBook_cover(cover);
        book.setBook_genre(form.getBook_genre());
        book.setBook_stock(form.getBook_stock());
        book.setBook_publisher(form.getBook_publisher());
        book.setBook_flag(form.getBook_flag() != null
        		? form.getBook_flag(): false);

        adminAddBookMapper.insert(book);
    }

}
