package bookstore.controller;

import bookstore.dto.RestResponse;
import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.book.BookDetailsDto;
import bookstore.dto.book.BookInfoDto;
import bookstore.dto.book.BookQueryDto;
import bookstore.dto.book.BookSaveDto;
import bookstore.service.book.BookService;
import bookstore.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class BookController {

    @Autowired
    private final BookService bookService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createBook(@Valid @RequestBody BookSaveDto bookSaveDto) {
        int id = bookService.createBook(bookSaveDto);
        return new RestResponse(String.valueOf(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookDetailsDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDetailsDto getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateBook(@PathVariable int id, @Valid @RequestBody BookSaveDto bookSaveDto) {
        bookService.updateBook(id, bookSaveDto);
        return new RestResponse(String.valueOf(true));
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new RestResponse(String.valueOf(true));
    }

    @GetMapping("/search")
    public List<BookInfoDto> search(@RequestBody BookQueryDto bookQueryDto) {
        return bookService.search(bookQueryDto);
    }

}
