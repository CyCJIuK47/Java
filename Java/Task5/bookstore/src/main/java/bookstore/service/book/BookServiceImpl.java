package bookstore.service.book;

import bookstore.dao.book.BookDao;
import bookstore.dto.book.BookDetailsDto;
import bookstore.dto.book.BookInfoDto;
import bookstore.dto.book.BookQueryDto;
import bookstore.dto.book.BookSaveDto;
import bookstore.exceptions.NotFoundException;
import bookstore.model.Book;
import bookstore.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public int createBook(BookSaveDto bookSaveDto) {
        Book book = new Book();
        updateModelFromDto(book, bookSaveDto);
        return bookDao.save(book);
    }

    @Override
    public BookDetailsDto getBook(int id) {
        Book book = getBookOrThrow(id);
        return convertToDetailsDto(book);
    }

    @Override
    public void updateBook(int id, BookSaveDto bookSaveDto) {
        Book book = getBookOrThrow(id);
        updateModelFromDto(book, bookSaveDto);
        bookDao.update(book);
    }

    @Override
    public void deleteBook(int id) {
        deleteBookOrThrow(id);
    }

    @Override
    public List<BookInfoDto> search(BookQueryDto bookQueryDto) {
        return bookDao.search(bookQueryDto.getTitle(), bookQueryDto.getAuthorId(),
                bookQueryDto.getPagination()).stream().map(
                this::convertToInfoDto
        ).toList();
    }

    @Override
    public List<BookDetailsDto> getAll(Pagination pagination) {
        return bookDao.getAll(pagination).stream()
                .map(this::convertToDetailsDto)
                .toList();
    }

    private void updateModelFromDto(Book book, BookSaveDto bookSaveDto) {
        book.setTitle(bookSaveDto.getTitle());
        book.setPages(bookSaveDto.getPages());
        book.setYear(bookSaveDto.getYear());
        book.setAuthorId(bookSaveDto.getAuthorId());
    }

    private Book getBookOrThrow(int id) {
        try {
            return bookDao.getById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Book with id %d not found".formatted(id));
        }
    }

    private void deleteBookOrThrow(int id) {
        try {
            bookDao.deleteById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Book with id %d not found".formatted(id));
        }
    }

    private BookDetailsDto convertToDetailsDto(Book book) {
        return BookDetailsDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .pages(book.getPages())
                .year(book.getYear())
                .authorId(book.getAuthorId())
                .build();
    }

    private BookInfoDto convertToInfoDto(Book book) {
        return BookInfoDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorId(book.getAuthorId())
                .build();
    }

}
