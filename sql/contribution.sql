use cafedev;

insert into feed(id, create_date, description, file_path, owner_id) values (1, CURRENT_TIMESTAMP(), 'Người lạ ơi', '/upload/java-01.png', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (2, CURRENT_TIMESTAMP(), 'Nơi tình yêu bắt đầu', '/upload/java-02.jpg', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (3, CURRENT_TIMESTAMP(), 'Người Tình Mùa Đông', '/upload/java-01.png', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (4, CURRENT_TIMESTAMP(), 'Tình thôi xót xa', '/upload/java-02.jpg', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (5, CURRENT_TIMESTAMP(), 'Em gái mưa', '/upload/java-01.png', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (6, CURRENT_TIMESTAMP(), 'Giấc Mơ Chỉ Là Giấc Mơ', '/upload/java-02.jpg', 1);
insert into feed(id, create_date, description, file_path, owner_id) values (7, CURRENT_TIMESTAMP(), 'I love hotel califoniha', '/upload/java-01.png', 2);
insert into feed(id, create_date, description, file_path, owner_id) values (8, CURRENT_TIMESTAMP(), 'Tình Khúc Vàng', '/upload/java-02.jpg', 2);
insert into feed(id, create_date, description, file_path, owner_id) values (9, CURRENT_TIMESTAMP(), 'Tình Nhạt Phai', '/upload/java-02.jpg', 2);
insert into feed(id, create_date, description, file_path, owner_id) values (10, CURRENT_TIMESTAMP(), 'Chỉ còn những mùa nhớ', '/upload/java-01.png', 2);
insert into feed(id, create_date, description, file_path, owner_id) values (11, CURRENT_TIMESTAMP(), 'Thà rằng như thế', '/upload/java-02.jpg', 2);
insert into feed(id, create_date, description, file_path, owner_id) values (12, CURRENT_TIMESTAMP(), 'I love hotel califoniha', '/upload/java-01.png', 2);

insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (1, 'Bài hát này thật hay', CURRENT_TIMESTAMP(), null, 1, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (2, 'Ca sỉ nữ hát hay quá', CURRENT_TIMESTAMP(), null, 1, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (3, 'Tuyệt vời, cảm thấy yêu đời', CURRENT_TIMESTAMP(), null, 1, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (4, 'Đà lạt thật đẹp', CURRENT_TIMESTAMP(), null, 1, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (5, 'Đồi núi thật thơ mọng và hữu tình', CURRENT_TIMESTAMP(), null, 2, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (6, 'Chiếc xe nhìn thú vị quá', CURRENT_TIMESTAMP(), null, 2, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (7, 'Hàng hiếm đó bạn', CURRENT_TIMESTAMP(), null, 2, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (8, 'Ừ chắc mắc tiền lắm', CURRENT_TIMESTAMP(), null, 2, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (9, 'Sợ có tiền cũng chẳn mua được', CURRENT_TIMESTAMP(), null, 3, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (10, 'Chợ Nhât Tảo bán có mà đầy', CURRENT_TIMESTAMP(), null, 3, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (11, 'BMW là chiếc xe đẹp nhất thế giới', CURRENT_TIMESTAMP(), null, 4, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (12, 'Ca Sỉ nam tên gì vậy mọi người', CURRENT_TIMESTAMP(), null, 4, 2);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (13, 'Ca sỉ nam tên là Minh Nhựt', CURRENT_TIMESTAMP(), null, 4, 1);
insert into comment(id, content, create_date, parent_id, feed_id, owner_id) values (14, 'I love him so much!', CURRENT_TIMESTAMP(), null, 5, 2);

delete from comment;

delete from feed;
select * from comment;