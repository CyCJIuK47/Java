package bookstore.dao.author;

import bookstore.model.Author;
import bookstore.utils.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AuthorDao {

    int save(Author author);

    Author getById(int id);

    void update(Author author);

    void deleteById(int id);

    List<Author> getAll();

    List<Author> search(String surname, String country, Pagination pagination);
}
