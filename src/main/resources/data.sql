INSERT INTO users (name, address, password, role)
VALUES
	('john doe', 'somewhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'user'),
	('jake', 'ooo', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'user'),
	('finn', 'ooo', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'operator'),
	('jane doe', 'nowhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'operator'),
	('glob', 'mars', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'admin'),
	('orgalorg', 'everywhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'admin');

INSERT INTO orders (user_id, order_date, order_status, order_title, order_description, order_sum_total)
VALUES
	(1, '1970-01-01', 'pending', 'order_1_title', 'order_1_description', '19.75'),
	(1, '2001-06-27', 'canceled', 'order_2_title', 'order_2_description', '199.95'),
	(3, '2025-10-15', 'delivered', 'order_3_title', 'order_3_description', '11.05'),
	(1, '1996-07-13', 'pending', 'order_4_title', 'order_4_description', '20.00'),
	(2, '2011-03-17', 'pending', 'order_5_title', 'order_5_description', '17.99'),
	(2, '2012-10-06', 'pending', 'order_6_title', 'order_6_description', '3.25');

INSERT INTO items (name, price)
VALUES
	('item one', '1.99'),
	('item two', '3.99'),
	('item three', '5.99'),
	('item four', '7.99'),
	('item five', '9.99'),
	('item six', '15.99'),
	('item seven', '20.99'),
	('item eight', '25.99'),
	('item nine', '30.99');

INSERT INTO ordered_items (order_id, item_id, quantity)
VALUES
	(1, 1, 2),
	(1, 3, 4),
	(1, 7, 1),
	(2, 1, 1),
	(2, 3, 1),
	(2, 7, 2),
	(2, 9, 1),
	(3, 2, 3),
	(3, 4, 1),
	(3, 6, 1),
	(4, 9, 1),
	(4, 7, 1),
	(5, 7, 1),
	(5, 8, 2),
	(5, 9, 1),
	(5, 3, 4),
	(6, 1, 1),
	(6, 2, 3),
	(6, 4, 1),
	(6, 5, 4),
	(6, 6, 1),
	(6, 7, 7),
	(6, 9, 1);
