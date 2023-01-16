delete from book;

alter table book auto_increment = 10;

insert into book(id, title, pages, year, id_author) values
(1, "Kobzar", 114, 1840, 1),
(2, "Haidamaky", 200, 1841, 1),
(3, "Try Lita", 300, 1847, 1),
(4, "Dumy i Mriji", 200, 1899, 2),
(5, "Lisova Pisnya", 100, 1911, 2),
(6, "Norwegian Wood", 900, 1987, 3);
