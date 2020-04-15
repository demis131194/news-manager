DELETE FROM news;
DELETE FROM authors;
DELETE FROM tags;

INSERT INTO authors(name, surname) VALUES
('Dima', 'Ford'),
('Vasya', 'Pupkin'),
('Sova', 'Sovna'),
('Artem', 'Hlebny'),
('Vasya', 'Asin'),
('Nikita', 'Semenov'),
('Dima', 'Ford'),
('Dima', 'Jackson');

INSERT INTO tags(name) VALUES
('History'),
('Science'),
('Comedy'),
('Nature'),
('Art'),
('My'),
('Dogs'),
('Cats');

INSERT INTO news(title, short_text, full_text, creation_date) VALUES
('Robokop', 'Short text 1', 'Full text 1', '2020-01-01 00:49:39'),
('WoRk', 'Short text 2', 'Full text 2', '2020-01-04 00:50:39'),
('work', 'Short text 3', 'Full text 3', '2020-01-02 00:50:39'),
('News', 'Short text 4', 'Full text 4', '2020-01-10 00:50:39'),
('Boring', 'Short text 5', 'Full text 5', '2020-01-13 02:50:39'),
('Bomb shel', 'Short text 6', 'Full text 6', '2020-01-14 02:50:39'),
('UFO', 'Short text 7', 'Full text 7', '2020-01-17 02:50:39'),
('Warning', 'Short text 8', 'Full text 8', '2020-01-15 01:50:39'),
('JAVA core', 'Short text 9', 'Full text 9', '2020-01-21 02:50:39'),
('Spring', 'Short text 10', 'Full text 10', '2020-01-23 02:50:39'),
('Postgresql', 'Short text 11', 'Full text 11', '2020-01-27 02:50:39');

INSERT INTO news_tags(news_id, tag_id) VALUES
(1, 2), (1, 3), (1, 6),
(2, 3), (2, 4), (2, 8), (2, 2),
(3, 3), (3, 5), (3, 8),
(4, 1), (4, 3), (4, 8), (4, 2), (4, 7),
(5, 1), (5, 3), (5, 5),
(6, 2), (6, 6), (6, 7),
(7, 1), (7, 2), (7, 3),
(8, 5), (8, 6),
(9, 2), (9, 5), (9, 6),
(10, 2), (10, 5), (10, 6),
(11, 1), (11, 2), (11, 3), (11, 7), (11, 8);

INSERT INTO news_authors(news_id, author_id) VALUES
(1, 1),
(2, 7),
(3, 3),
(4, 4),
(5, 5),
(6, 8),
(7, 4),
(8, 7),
(9, 8),
(10, 1),
(11, 1);

INSERT INTO users(id, name, surname, login, password) VALUES
(1, 'Admin', 'Admin', 'admin', '$2a$10$BDEDg7UPuDauJ.O1i/NsCee4NczrOqOTop/WhPwHRC2V8u76iYBVu'),
(2, 'User', 'User', 'user', '$2a$10$0LhnCXwJiXwUOuUM6Y1PlunjqvGGAbVlNQmCpCS1QEjep56dHILSa');

INSERT INTO roles(user_id, role_name) VALUES
(1, 'ADMIN'),
(2, 'USER');