INSERT INTO users (name, address, password, role)
VALUES
	('john doe', 'somewhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'user'),
	('jake', 'ooo', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'user'),
	('finn', 'ooo', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'operator'),
	('jane doe', 'nowhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'operator'),
	('glob', 'mars', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'admin'),
	('orgalorg', 'everywhere', '$2a$10$El65VVvQSvFituLaf9seU.hhEDPAs.l7.8/VfJPiTb1IirukqLp0S', 'admin');

INSERT INTO orders (user_id, order_date, order_status, order_title, order_description)
VALUES
	(1, '1970-01-01', 'pending', 'order_1_title', 'order_1_description'),
	(1, '2001-06-27', 'canceled', 'order_2_title', 'order_2_description'),
	(3, '2025-10-15', 'delivered', 'order_3_title', 'order_3_description'),
	(1, '1996-07-13', 'pending', 'order_4_title', 'order_4_description'),
	(2, '2011-03-17', 'pending', 'order_5_title', 'order_5_description'),
	(2, '2012-10-06', 'pending', 'order_6_title', 'order_6_description');

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

INSERT INTO ordered_items (order_id, name, price, quantity)
VALUES
	(1, 'item one', '1.99', 2),
	(1, 'item two', '3.99', 4),
	(1, 'item three', '5.99', 1),
	(2, 'item four', '7.99', 1),
	(2, 'item five', '9.99', 1),
	(2, 'item six', '15.99', 2),
	(2, 'item seven', '20.99', 1),
	(3, 'item eight', '25.99', 3),
	(3, 'item nine', '30.99', 1),
	(3, 'item one', '1.99', 1),
	(4, 'item two', '3.99', 1),
	(4, 'item three', '5.99', 1),
	(5, 'item four', '7.99', 1),
	(5, 'item five', '9.99', 2),
	(5, 'item six', '15.99', 1),
	(5, 'item seven', '20.99', 4),
	(6, 'item eight', '25.99', 1),
	(6, 'item nine', '30.99', 3),
	(6, 'item one', '1.99', 1),
	(6, 'item two', '3.99', 4),
	(6, 'item three', '5.99', 1),
	(6, 'item four', '7.99', 7),
	(6, 'item five', '9.99', 1);
