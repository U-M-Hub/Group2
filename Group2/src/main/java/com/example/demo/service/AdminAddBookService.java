package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Books;
import com.example.demo.form.BookForm;
import com.example.demo.mapper.AdminAddBookMapper;


@Service
public class AdminAddBookService {
	
	private final AdminAddBookMapper adminAddBookMapper;

	//コンストラクタ
    public AdminAddBookService(AdminAddBookMapper adminAddBookMapper) {
        this.adminAddBookMapper = adminAddBookMapper;
    }

    //書籍を登録する処理
    @Transactional
    public void register(BookForm form) {
        //DBに登録する書籍オブジェクトを作成する
    	Books book = new Books();
        //あらすじが未入力なら「未登録」にする
    	String summary = form.getBook_summary();
        if(summary == null || summary.isBlank()) {
        	summary = "未登録"; 
        }
        //画像が未選択なら「Noimage.png」にする
        String cover= form.getBook_cover();
        if(cover == null || cover.isBlank()) {
        	cover = "Noimage.png";
        }

        //フォームの入力値をBooksに設定する
        //書籍名
        book.setBook_title(form.getBook_title());
        //著者名
        book.setBook_author(form.getBook_author());
        //価格
        book.setBook_price(form.getBook_price());
        //あらすじ
        book.setBook_summary(summary);
        //表紙画像
        book.setBook_cover(cover);
        //ジャンル
        book.setBook_genre(form.getBook_genre());
        //在庫数
        book.setBook_stock(form.getBook_stock());
        //出版社名
        book.setBook_publisher(form.getBook_publisher());
        //公開状態を設定する。NULLの場合はOFFにする
        book.setBook_flag(form.getBook_flag() != null
        		? form.getBook_flag(): false);

        adminAddBookMapper.insert(book);
    }

}
