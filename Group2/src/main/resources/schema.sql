
-- 書籍ECサイト：テーブル初期化
-- 開発用：起動のたびに既存テーブルを削除・再作成

-- ====================================
-- 1. 既存テーブルの削除
-- ====================================

DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS users;


-- ====================================
-- 2. genres（ジャンル情報）
-- ====================================

CREATE TABLE genres (
    genre_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    genre_name VARCHAR(20) NOT NULL UNIQUE
);


-- ====================================
-- 3. users（ユーザー情報）
-- ====================================

CREATE TABLE users (
    user_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(20) NOT NULL
);


-- ====================================
-- 4. books（書籍情報）
-- ====================================

CREATE TABLE books (
    book_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_title VARCHAR(255) NOT NULL,
    book_author VARCHAR(255) NOT NULL,
    book_price INTEGER NOT NULL CHECK (book_price >= 1),
    book_summary TEXT NOT NULL DEFAULT '未登録',
    book_cover VARCHAR NOT NULL,
    book_genre INTEGER NOT NULL REFERENCES genres(genre_id),
    book_stock INTEGER NOT NULL CHECK (book_stock >= 0),
    book_publisher VARCHAR NOT NULL,
    book_flag BOOLEAN NOT NULL DEFAULT FALSE
);


-- ====================================
-- 5. cart（カート情報）
-- ====================================

CREATE TABLE cart (
    user_id INTEGER NOT NULL REFERENCES users(user_id),
    book_id INTEGER NOT NULL REFERENCES books(book_id),
    book_count INTEGER NOT NULL CHECK (book_count >= 1),

    PRIMARY KEY (user_id, book_id)
);


-- ====================================
-- 6. order_details（注文明細情報）
-- ====================================

CREATE TABLE order_details (
    order_detail_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(user_id),
    order_number INTEGER NOT NULL,
    order_branch_number INTEGER NOT NULL CHECK (order_branch_number >= 1),
    book_id INTEGER NOT NULL REFERENCES books(book_id),
    order_price INTEGER NOT NULL CHECK (order_price >= 0),
    book_count INTEGER NOT NULL CHECK (book_count >= 1),
    order_date DATE NOT NULL,

    CONSTRAINT uq_order_number_branch
        UNIQUE (order_number, order_branch_number)
);
