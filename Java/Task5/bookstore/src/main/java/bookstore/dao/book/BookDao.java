package bookstore.dao.book;

import bookstore.model.Author;
import bookstore.model.Book;
import bookstore.utils.Pagination;

import java.util.List;

public interface BookDao {

    int save(Book book);

    Book getById(int id);

    void update(Book book);

    void deleteById(int id);

    List<Book> getAll(Pagination pagination);

    List<Book> search(String title, int authorId, Pagination pagination);
}