package bookstore.dao.book;

import bookstore.model.Book;
import bookstore.utils.Pagination;
import bookstore.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int save(Book book) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "insert into book (title, pages, year, id_author) values (?,?,?,?)";
        Object[] args = new Object[] {book.getId(), book.getTitle(), book.getPages(),
                book.getYear(), book.getAuthorId()};

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, new String[]{"id"});
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPages());
            ps.setInt(3, book.getYear());
            ps.setInt(4, book.getAuthorId());
            return ps;
        }, keyHolder);

        if (rowsAffected == 0) {
            throw new RuntimeException("Author save failed with id=" + book.getId());
        }

        return keyHolder.getKey().intValue();
    }

    @Override
    public Book getById(int id) {
        String query = "select id, title, pages, year, id_author from book where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper(), new Object[]{id});
        return book;
    }

    @Override
    public void update(Book book) {
        String query = "update book set title=?, pages=?, year=?, id_author=? where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {book.getTitle(), book.getPages(), book.getYear(), book.getAuthorId(), book.getId()};

        int rowsAffected = jdbcTemplate.update(query, args);
        if (rowsAffected == 0) {
            throw new RuntimeException("No book found with id=" + book.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from book where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int rowsAffected = jdbcTemplate.update(query, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No book found with id=" + id);
        }
    }

    @Override
    public List<Book> getAll(Pagination pagination) {
        String query = "select id, title, pages, year, id_author from book limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {pagination.getOffset(), pagination.getLimit()};

        List<Book> bookList = jdbcTemplate.query(query, new BookRowMapper(), args);
        return bookList;
    }

    @Override
    public List<Book> search(String title, int authorId, Pagination pagination) {

        if (!StringUtils.isNullOrEmptyOrBlank(title) && authorId!=0) {
            return searchByTitleAndAuthorId(title, authorId, pagination);
        }
        if (StringUtils.isNullOrEmptyOrBlank(title) && authorId!=0) {
            return searchByAuthorId(authorId, pagination);
        }
        if (!StringUtils.isNullOrEmptyOrBlank(title) && authorId==0) {
            return searchByTitle(title, pagination);
        }

        // if no filters provided - return all
        return getAll(pagination);
    }

    private List<Book> searchByTitle(String title, Pagination pagination) {
        String query = "select id, title, pages, year, id_author from book where title=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {title, pagination.getOffset(), pagination.getLimit()};

        List<Book> bookList = jdbcTemplate.query(query, new BookRowMapper(), args);
        return bookList;
    }

    private List<Book> searchByAuthorId(int authorId, Pagination pagination) {
        String query = "select id, title, pages, year, id_author from book where id_author=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {authorId, pagination.getOffset(), pagination.getLimit()};

        List<Book> bookList = jdbcTemplate.query(query, new BookRowMapper(), args);
        return bookList;
    }

    private List<Book> searchByTitleAndAuthorId(String title, int authorId, Pagination pagination) {
        String query = "select id, title, pages, year, id_author from book where title=? and id_author=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {title, authorId, pagination.getOffset(), pagination.getLimit()};

        List<Book> bookList = jdbcTemplate.query(query, new BookRowMapper(), args);
        return bookList;
    }
    private class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("pages"),
                    rs.getInt("year"),
                    rs.getInt("id_author")
            );
        }
    }
}
