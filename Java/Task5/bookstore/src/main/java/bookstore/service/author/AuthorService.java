package bookstore.service.author;

import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.author.AuthorInfoDto;
import bookstore.dto.author.AuthorQueryDto;
import bookstore.dto.author.AuthorSaveDto;
import bookstore.utils.Pagination;

import java.util.List;

public interface AuthorService {

    int createAuthor(AuthorSaveDto authorSaveDto);

    AuthorDetailsDto getAuthor(int id);

    void updateAuthor(int id, AuthorSaveDto authorSaveDto);

    void deleteAuthor(int id);

    List<AuthorInfoDto> search(AuthorQueryDto authorQueryDto);

    List<AuthorDetailsDto> getAll(Pagination pagination);
}


