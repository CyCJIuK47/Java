DROP DATABASE IF EXISTS bookstoredb;
CREATE DATABASE bookstoredb;

USE bookstoredb;

DROP TABLE IF EXISTS author;
CREATE TABLE author (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255) NOT NULL,
         surname VARCHAR(255) NOT NULL,
         country VARCHAR(255) NOT NULL )
ENGINE=INNODB;

create index idx_author_country on author(country);
create index idx_author_surname on author(surname);
create index idx_author_country_surname on author(country, surname);

insert into author(id, name, surname, country) values
(1, "Taras", "Shevchenko", "Ukraine"),
(2, "Lesya", "Ukrainka", "Ukraine"),
(3, "Haruki", "Murakami", "Japan");

DROP TABLE IF EXISTS book;
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pages INT NOT NULL,
    year INT NOT NULL,
    id_author INT NOT NULL,
    FOREIGN KEY (id_author) REFERENCES author(id) ON DELETE CASCADE )
ENGINE=INNODB;

create index idx_book_title on book(title);
create index idx_book_id_author on book(id_author);
create index idx_book_id_author_title on book(id_author, title);

insert into book(id, title, pages, year, id_author) values
(1, "Kobzar", 114, 1840, 1),
(2, "Haidamaky", 200, 1841, 1),
(3, "Try Lita", 300, 1847, 1),
(4, "Dumy i Mriji", 200, 1899, 2),
(5, "Lisova Pisnya", 100, 1911, 2),
(6, "Norwegian Wood", 900, 1987, 3);

DROP DATABASE IF EXISTS bookstoretestdb;
CREATE DATABASE bookstoretestdb;

USE bookstoretestdb;

DROP TABLE IF EXISTS author;
CREATE TABLE author (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255) NOT NULL,
         surname VARCHAR(255) NOT NULL,
         country VARCHAR(255) NOT NULL )
ENGINE=INNODB;

create index idx_author_country on author(country);
create index idx_author_surname on author(surname);
create index idx_author_country_surname on author(country, surname);

DROP TABLE IF EXISTS book;
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pages INT NOT NULL,
    year INT NOT NULL,
    id_author INT NOT NULL,
    FOREIGN KEY (id_author) REFERENCES author(id) ON DELETE CASCADE )
ENGINE=INNODB;

create index idx_book_title on book(title);
create index idx_book_id_author on book(id_author);
create index idx_book_id_author_title on book(id_author, title);