package bookstore.controller;

import bookstore.BookstoreApplication;
import bookstore.dto.RestResponse;
import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.book.BookDetailsDto;
import bookstore.dto.book.BookInfoDto;
import bookstore.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static bookstore.test.helpers.parsers.ResponseParser.parseListResponse;
import static bookstore.test.helpers.parsers.ResponseParser.parseResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BookstoreApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/create-author-before.sql", "/create-book-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-book-after.sql", "/create-author-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBookSuccess() throws Exception {
        String title = "Kaminnyj hospodar";
        int pages = 100;
        int year = 1912;
        int authorId = 1;
        String body = """
          {
              "title": "%s",
              "pages": %d,
              "year": %d,
              "authorId": %d
          }
        """.formatted(title, pages, year, authorId);
        MvcResult mvcResult = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andReturn();

        RestResponse response = parseResponse(objectMapper, mvcResult, RestResponse.class);
        int generatedId = Integer.parseInt(response.getResult());
        assertEquals(10, generatedId);
    }

    @Test
    void createBookFailed() throws Exception {
        String body = """
                {}
                """;
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAuthorThenAssignBook() throws Exception {
        String name = "Ivan";
        String surname = "Franko";
        String country = "Ukraine";
        String body = """
          {
              "name": "%s",
              "surname": "%s",
              "country": "%s"
          }
        """.formatted(name, surname, country);
        MvcResult mvcResult = mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andReturn();

        RestResponse response = parseResponse(objectMapper, mvcResult, RestResponse.class);
        int authorGeneratedId = Integer.parseInt(response.getResult());

        String title = "Kaminnyj hospodar";
        int pages = 150;
        int year = 1883;
        int authorId = authorGeneratedId;
        body = """
          {
              "title": "%s",
              "pages": %d,
              "year": %d,
              "authorId": %d
          }
        """.formatted(title, pages, year, authorId);
        mvcResult = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andReturn();

        response = parseResponse(objectMapper, mvcResult, RestResponse.class);
        int bookGeneratedId = Integer.parseInt(response.getResult());
        assertEquals(10, bookGeneratedId);
    }

    @Test
    void createBookWithUnknownAuthorFailed() throws Exception {

        String title = "Kaminnyj hospodar";
        int pages = 150;
        int year = 1883;
        int authorId = 100;
        String body = """
          {
              "title": "%s",
              "pages": %d,
              "year": %d,
              "authorId": %d
          }
        """.formatted(title, pages, year, authorId);
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getBookSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andReturn();

        Book responseBook = parseResponse(objectMapper, mvcResult, Book.class);
        Book expectedBook = new Book(1, "Kobzar", 114, 1840, 1);
        assertEquals(expectedBook, responseBook);
    }

    @Test
    void getBookFailed() throws Exception {
        mockMvc.perform(get("/api/books/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBookSuccess() throws Exception {
        // update "Dumy i Mriji" to "Dumy ta Mriji"
        String title = "Dumy ta Mriji";
        int pages = 200;
        int year = 1899;
        int authorId = 2;
        String body = """
          {
              "title": "%s",
              "pages": %d,
              "year": %d,
              "authorId": %d
          }
        """.formatted(title, pages, year, authorId);
        mockMvc.perform(put("/api/books/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/api/books/4"))
                .andExpect(status().isOk())
                .andReturn();

        Book responseBook = parseResponse(objectMapper, mvcResult, Book.class);
        Book expectedBook = new Book(4, title, pages, year, authorId);
        assertEquals(expectedBook, responseBook);
    }

    @Test
    void updateBookFailed() throws Exception {
        String title = "Dumy ta Mriji";
        int pages = 200;
        int year = 1899;
        int authorId = 2;
        String body = """
          {
              "title": "%s",
              "pages": %d,
              "year": %d,
              "authorId": %d
          }
        """.formatted(title, pages, year, authorId);
        mockMvc.perform(put("/api/books/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBookSuccess() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteBookFailed() throws Exception {
        mockMvc.perform(delete("/api/books/100"))
                .andExpect(status().isNotFound());
    }


    @Test
    void searchByTitleSuccess() throws Exception {
        String title = "Kobzar";
        int offset = 0;
        int limit = 2;
        String body = """
          {
              "title": "%s",
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(title, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);
        assertTrue(bookInfoDtoList.toArray().length <= limit);
        assertEquals(1, bookInfoDtoList.toArray().length);
        assertEquals(title, bookInfoDtoList.get(0).getTitle());
    }

    @Test
    void searchByAuthorIdSuccess() throws Exception {
        int authorId = 1;
        int offset = 0;
        int limit = 10;
        String body = """
          {
              "authorId": %d,
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(authorId, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);
        assertTrue(bookInfoDtoList.toArray().length <= limit);
        assertEquals(3, bookInfoDtoList.toArray().length);
        assertEquals(authorId, bookInfoDtoList.get(0).getAuthorId());
    }

    @Test
    void searchByTitleAndAuthorIdSuccess() throws Exception {
        String title = "Lisova Pisnya";
        int authorId = 2;
        int offset = 0;
        int limit = 5;
        String body = """
          {
              "title": "%s",
              "authorId": %d,
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(title, authorId, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);

        assertTrue(bookInfoDtoList.toArray().length <= limit);
        assertEquals(1, bookInfoDtoList.toArray().length);
        assertEquals(title, bookInfoDtoList.get(0).getTitle());
        assertEquals(authorId, bookInfoDtoList.get(0).getAuthorId());
    }

    @Test
    void getAll() throws Exception {
        String body = """
                {
                }
                """;
        MvcResult mvcResult = mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<BookDetailsDto> bookDetailsDtosList = parseListResponse(objectMapper, mvcResult, BookDetailsDto.class);
        assertEquals(6, bookDetailsDtosList.toArray().length);
    }

    @Test
    void searchPaginationTest() throws Exception {
        int authorId = 1;
        String body = """
          {
              "authorId": %d,
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """;
        MvcResult mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(authorId, 0, 5)))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList1 = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);

        mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(authorId, 0, 2)))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList2 = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);

        mvcResult = mockMvc.perform(get("/api/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(authorId, 2, 5)))
                .andExpect(status().isOk())
                .andReturn();

        List<BookInfoDto> bookInfoDtoList3 = parseListResponse(objectMapper, mvcResult, BookInfoDto.class);
        bookInfoDtoList2.addAll(bookInfoDtoList3);

        //assert that [offset=0, limit=5] equals [offset=0, limit=2] + [offset=2, limit=5]
        // when there are total 3 books in db
        assertIterableEquals(bookInfoDtoList1, bookInfoDtoList2);
    }

}