package bookstore.service.book;

import bookstore.dto.book.BookDetailsDto;
import bookstore.dto.book.BookInfoDto;
import bookstore.dto.book.BookQueryDto;
import bookstore.dto.book.BookSaveDto;
import bookstore.utils.Pagination;

import java.util.List;

public interface BookService {

    int createBook(BookSaveDto bookSaveDto);

    BookDetailsDto getBook(int id);

    void updateBook(int id, BookSaveDto bookSaveDto);

    void deleteBook(int id);

    List<BookInfoDto> search(BookQueryDto bookQueryDto);

    List<BookDetailsDto> getAll(Pagination pagination);
}


