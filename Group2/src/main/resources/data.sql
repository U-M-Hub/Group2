-- 1. ジャンル：4件
INSERT INTO genres (genre_name) VALUES
    ('参考書'),
    ('漫画'),
    ('小説'),
    ('図鑑');

-- 2. サンプルユーザー：1件
-- 注意：user_passwordは演習用の指定値をそのまま保存しています。
-- 将来ログインを実装する際は、パスワードハッシュを保存してください。
INSERT INTO users (user_name, user_password, user_role) VALUES
    ('sampleuser', 'userpass', '消費者');

-- 3. 書籍：20件（A～T）
-- 表紙画像 Noimage.png は別途 static/images/ に配置します。
INSERT INTO books (
    book_title, book_author, book_price, book_summary,
    book_cover, book_genre, book_stock, book_publisher, book_flag
) VALUES
    ('サンプル本A', 'サンプル著者', 666, 'サンプルあらすじ', 'Noimage.png', 4, 24, 'サンプル出版社', TRUE),
    ('サンプル本B', 'サンプル著者', 1217, 'サンプルあらすじ', 'Noimage.png', 2, 23, 'サンプル出版社', FALSE),
    ('サンプル本C', 'サンプル著者', 706, 'サンプルあらすじ', 'Noimage.png', 1, 5, 'サンプル出版社', TRUE),
    ('サンプル本D', 'サンプル著者', 918, 'サンプルあらすじ', 'Noimage.png', 2, 16, 'サンプル出版社', TRUE),
    ('サンプル本E', 'サンプル著者', 416, 'サンプルあらすじ', 'Noimage.png', 3, 15, 'サンプル出版社', TRUE),
    ('サンプル本F', 'サンプル著者', 294, 'サンプルあらすじ', 'Noimage.png', 4, 19, 'サンプル出版社', TRUE),
    ('サンプル本G', 'サンプル著者', 1233, 'サンプルあらすじ', 'Noimage.png', 3, 4, 'サンプル出版社', TRUE),
    ('サンプル本H', 'サンプル著者', 1168, 'サンプルあらすじ', 'Noimage.png', 3, 13, 'サンプル出版社', TRUE),
    ('サンプル本I', 'サンプル著者', 144, 'サンプルあらすじ', 'Noimage.png', 4, 25, 'サンプル出版社', TRUE),
    ('サンプル本J', 'サンプル著者', 287, 'サンプルあらすじ', 'Noimage.png', 3, 18, 'サンプル出版社', TRUE),
    ('サンプル本K', 'サンプル著者', 328, 'サンプルあらすじ', 'Noimage.png', 3, 10, 'サンプル出版社', TRUE),
    ('サンプル本L', 'サンプル著者', 1567, 'サンプルあらすじ', 'Noimage.png', 2, 11, 'サンプル出版社', TRUE),
    ('サンプル本M', 'サンプル著者', 688, 'サンプルあらすじ', 'Noimage.png', 1, 7, 'サンプル出版社', TRUE),
    ('サンプル本N', 'サンプル著者', 1197, 'サンプルあらすじ', 'Noimage.png', 2, 8, 'サンプル出版社', FALSE),
    ('サンプル本O', 'サンプル著者', 682, 'サンプルあらすじ', 'Noimage.png', 2, 2, 'サンプル出版社', TRUE),
    ('サンプル本P', 'サンプル著者', 1609, 'サンプルあらすじ', 'Noimage.png', 3, 19, 'サンプル出版社', TRUE),
    ('サンプル本Q', 'サンプル著者', 1909, 'サンプルあらすじ', 'Noimage.png', 3, 0, 'サンプル出版社', TRUE),
    ('サンプル本R', 'サンプル著者', 1005, 'サンプルあらすじ', 'Noimage.png', 4, 20, 'サンプル出版社', TRUE),
    ('サンプル本S', 'サンプル著者', 1412, 'サンプルあらすじ', 'Noimage.png', 2, 1, 'サンプル出版社', TRUE),
    ('サンプル本T', 'サンプル著者', 946, 'サンプルあらすじ', 'Noimage.png', 1, 13, 'サンプル出版社', FALSE),
    ('サンプル本U', 'サンプル著者', 142, 'サンプルあらすじ', 'Noimage.png', 2, 15, 'サンプル出版社', TRUE),
    ('サンプル本V', 'サンプル著者', 212, 'サンプルあらすじ', 'Noimage.png', 1, 29, 'サンプル出版社', TRUE),
    ('サンプル本W', 'サンプル著者', 412, 'サンプルあらすじ', 'Noimage.png', 1, 25, 'サンプル出版社', TRUE),
    ('サンプル本X', 'サンプル著者', 762, 'サンプルあらすじ', 'Noimage.png', 3, 4, 'サンプル出版社', TRUE),
    ('サンプル本Y', 'サンプル著者', 342, 'サンプルあらすじ', 'Noimage.png', 4, 3, 'サンプル出版社', TRUE);


-- 4. cart：初期データなし（INSERTしない）

-- 5. 注文明細：3件
-- 対応する書籍の現在価格をbooksから取得して保存します。
-- 指定の日付は 2024/08/07、2025/03/15、2026/10/01 と解釈しています。
INSERT INTO order_details (
    user_id, order_number, order_branch_number,
    book_id, order_price, book_count, order_date
)
SELECT
    u.user_id,
    v.order_number,
    1,
    b.book_id,
    b.book_price,
    1,
    v.order_date
FROM (
    VALUES
        (101, 1, DATE '2024-08-07'),
        (102, 2, DATE '2025-03-15'),
        (103, 3, DATE '2026-10-01')
) AS v(order_number, book_id, order_date)
JOIN books AS b ON b.book_id = v.book_id
JOIN users AS u ON u.user_name = 'sampleuser'
ORDER BY v.order_number;