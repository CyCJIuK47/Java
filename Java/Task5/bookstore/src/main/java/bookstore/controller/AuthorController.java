package bookstore.controller;

import bookstore.dto.RestResponse;
import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.author.AuthorInfoDto;
import bookstore.dto.author.AuthorQueryDto;
import bookstore.dto.author.AuthorSaveDto;
import bookstore.service.author.AuthorService;
import bookstore.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

    @Autowired
    private final AuthorService authorService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createAuthor(@Valid @RequestBody AuthorSaveDto authorSaveDto) {
        System.out.println(authorSaveDto);
        int id = authorService.createAuthor(authorSaveDto);
        return new RestResponse(String.valueOf(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDetailsDto> getAll(@RequestBody Pagination pagination) {
        if (pagination == null) {
            pagination = new Pagination(0, 50);
        }
        return authorService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public AuthorDetailsDto getAuthor(@PathVariable int id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAuthor(@Valid @PathVariable int id, @RequestBody AuthorSaveDto authorSaveDto) {
        authorService.updateAuthor(id, authorSaveDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/search")
    public List<AuthorInfoDto> search(@RequestBody AuthorQueryDto authorQueryDto) {
        return authorService.search(authorQueryDto);
    }
}
