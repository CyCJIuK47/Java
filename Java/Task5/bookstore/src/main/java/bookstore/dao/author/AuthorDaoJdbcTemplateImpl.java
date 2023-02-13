package bookstore.dao.author;

import bookstore.model.Author;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int save(Author author) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "insert into author (name, surname, country) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, new String[]{"id"});
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getCountry());
            return ps;
        }, keyHolder);

        if (rowsAffected == 0) {
            throw new RuntimeException("Author save failed with id=" + author.getId());
        }

        return keyHolder.getKey().intValue();
    }

    @Override
    public Author getById(int id) {
        String query = "select id, name, surname, country from author where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Author author = jdbcTemplate.queryForObject(query, new AuthorRowMapper(), new Object[]{id});
        return author;
    }

    @Override
    public void update(Author author) {
        String query = "update author set name=?, surname=?, country=? where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {author.getName(), author.getSurname(), author.getCountry(), author.getId()};

        int rowsAffected = jdbcTemplate.update(query, args);
        if (rowsAffected == 0) {
            throw new RuntimeException("No author found with id=" + author.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from author where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int rowsAffected = jdbcTemplate.update(query, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No author found with id=" + id);
        }
    }

    @Override
    public List<Author> getAll() {
        String query = "select id, name, surname, country from author";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {};

        List<Author> authorList = jdbcTemplate.query(query, new AuthorRowMapper(), args);
        return authorList;
    }

    @Override
    public List<Author> search(String surname, String country, Pagination pagination) {

        if (!StringUtils.isNullOrEmptyOrBlank(surname) && !StringUtils.isNullOrEmptyOrBlank(country)) {
            return searchBySurnameAndCountry(surname, country, pagination);
        }
        if (StringUtils.isNullOrEmptyOrBlank(surname) && !StringUtils.isNullOrEmptyOrBlank(country)) {
            return searchByCountry(country, pagination);
        }
        if (StringUtils.isNullOrEmptyOrBlank(country) && !StringUtils.isNullOrEmptyOrBlank(surname)) {
            return searchBySurname(surname, pagination);
        }

        // if no filters provided - return all
        return getAll();
    }

    private List<Author> searchBySurname(String surname, Pagination pagination) {
        String query = "select id, name, surname, country from author where surname=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {surname, pagination.getOffset(), pagination.getLimit()};

        List<Author> authorList = jdbcTemplate.query(query, new AuthorRowMapper(), args);
        return authorList;
    }

    private List<Author> searchByCountry(String country, Pagination pagination) {
        String query = "select id, name, surname, country from author where country=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {country, pagination.getOffset(), pagination.getLimit()};

        List<Author> authorList = jdbcTemplate.query(query, new AuthorRowMapper(), args);
        return authorList;
    }

    private List<Author> searchBySurnameAndCountry(String surname, String country, Pagination pagination) {
        String query = "select id, name, surname, country from author where surname=? and country=? limit ?, ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {surname, country, pagination.getOffset(), pagination.getLimit()};

        List<Author> authorList = jdbcTemplate.query(query, new AuthorRowMapper(), args);
        return authorList;
    }

    private class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("country")
            );
        }
    }
}
