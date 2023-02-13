package bookstore.service.author;

import bookstore.dao.author.AuthorDao;
import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.author.AuthorInfoDto;
import bookstore.dto.author.AuthorQueryDto;
import bookstore.dto.author.AuthorSaveDto;
import bookstore.exceptions.NotFoundException;
import bookstore.model.Author;
import bookstore.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public int createAuthor(AuthorSaveDto authorSaveDto) {
        Author author = new Author();
        updateModelFromDto(author, authorSaveDto);
        return authorDao.save(author);
    }

    @Override
    public AuthorDetailsDto getAuthor(int id) {
        Author author = getAuthorOrThrow(id);
        return convertToDetailsDto(author);
    }

    @Override
    public void updateAuthor(int id, AuthorSaveDto authorSaveDto) {
        Author author = getAuthorOrThrow(id);
        updateModelFromDto(author, authorSaveDto);
        authorDao.update(author);
    }

    @Override
    public void deleteAuthor(int id) {
        deleteAuthorOrThrow(id);
    }

    @Override
    public List<AuthorInfoDto> search(AuthorQueryDto authorQueryDto) {
        return authorDao.search(authorQueryDto.getSurname(), authorQueryDto.getCountry(),
                authorQueryDto.getPagination()).stream().map(
                    this::convertToInfoDto
        ).toList();
    }

    @Override
    public List<AuthorDetailsDto> getAll() {
        return authorDao.getAll().stream()
                .map(this::convertToDetailsDto)
                .toList();
    }

    private void updateModelFromDto(Author author, AuthorSaveDto authorSaveDto) {
        author.setName(authorSaveDto.getName());
        author.setSurname(authorSaveDto.getSurname());
        author.setCountry(authorSaveDto.getCountry());
    }

    private Author getAuthorOrThrow(int id) {
        try {
            return authorDao.getById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Author with id %d not found".formatted(id));
        }
    }

    private void deleteAuthorOrThrow(int id) {
        try {
            authorDao.deleteById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Author with id %d not found".formatted(id));
        }
    }

    private AuthorDetailsDto convertToDetailsDto(Author author) {
        return AuthorDetailsDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .country(author.getCountry())
                .build();
    }

    private AuthorInfoDto convertToInfoDto(Author author) {
        return AuthorInfoDto.builder()
                .id(author.getId())
                .fullName(author.getName() + " " + author.getSurname())
                .country(author.getCountry())
                .build();
    }

}
