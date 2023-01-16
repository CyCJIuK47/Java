delete from author;

alter table author auto_increment = 10;

insert into author(id, name, surname, country) values
(1, "Taras", "Shevchenko", "Ukraine"),
(2, "Lesya", "Ukrainka", "Ukraine"),
(3, "Haruki", "Murakami", "Japan");

