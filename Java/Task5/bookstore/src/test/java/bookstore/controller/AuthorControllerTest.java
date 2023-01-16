package bookstore.controller;

import bookstore.BookstoreApplication;
import bookstore.dto.RestResponse;
import bookstore.dto.author.AuthorDetailsDto;
import bookstore.dto.author.AuthorInfoDto;
import bookstore.model.Author;
import bookstore.test.helpers.parsers.ResponseParser;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static bookstore.test.helpers.parsers.ResponseParser.parseResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BookstoreApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/create-author-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-author-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAuthorSuccess() throws Exception {
        String name = "Vasyl";
        String surname = "Stus";
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
        int generatedId = Integer.parseInt(response.getResult());
        assertEquals(10, generatedId);
    }

    @Test
    void createAuthorFailed() throws Exception {
        String body = """
          {
          }
        """;
        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAuthorSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andReturn();

        Author responseAuthor = ResponseParser.parseResponse(objectMapper, mvcResult, Author.class);
        Author expectedAuthor = new Author(1, "Taras", "Shevchenko", "Ukraine");
        assertEquals(expectedAuthor, responseAuthor);
    }

    @Test
    void getAuthorFailed() throws Exception {
        mockMvc.perform(get("/api/authors/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAuthorSuccess() throws Exception {
        // update Lesya to Lesia
        String name = "Lesia";
        String surname = "Ukrainka";
        String country = "Ukraine";
        String body = """
          {
              "name": "%s",
              "surname": "%s",
              "country": "%s"
          }
        """.formatted(name, surname, country);
        mockMvc.perform(put("/api/authors/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/api/authors/2"))
                .andExpect(status().isOk())
                .andReturn();

        Author responseAuthor = ResponseParser.parseResponse(objectMapper, mvcResult, Author.class);
        Author expectedAuthor = new Author(2, name, surname, country);
        assertEquals(expectedAuthor, responseAuthor);
    }

    @Test
    void updateAuthorFailed() throws Exception {
        String name = "Lesya";
        String surname = "Ukrainka";
        String country = "Ukraine";
        String body = """
          {
              "name": "%s",
              "surname": "%s",
              "country": "%s"
          }
        """.formatted(name, surname, country);
        mockMvc.perform(put("/api/authors/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAuthorSuccess() throws Exception {
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteAuthorFailed() throws Exception {
        mockMvc.perform(delete("/api/authors/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchByCountrySuccess() throws Exception {
        String surname = null;
        String country = "Ukraine";
        int offset = 0;
        int limit = 2;
        String body = """
          {
              "surname": %s,
              "country": "%s",
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(surname, country, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);
        assertTrue(authorInfoDtoList.toArray().length <= limit);
        assertEquals(2, authorInfoDtoList.toArray().length);
        assertEquals(country, authorInfoDtoList.get(0).getCountry());
    }

    @Test
    void searchBySurnameSuccess() throws Exception {
        String surname = null;
        String country = "Japan";
        int offset = 0;
        int limit = 2;
        String body = """
          {
              "country": "%s",
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(country, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);
        assertTrue(authorInfoDtoList.toArray().length <= limit);
        assertEquals(1, authorInfoDtoList.toArray().length);
        assertEquals(country, authorInfoDtoList.get(0).getCountry());
    }

    @Test
    void searchBySurnameAndCountrySuccess() throws Exception {
        String surname = "Ukrainka";
        String country = "Ukraine";
        int offset = 0;
        int limit = 2;
        String body = """
          {
              "surname": "%s",
              "country": "%s",
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """.formatted(surname, country, offset, limit);
        MvcResult mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);

        assertTrue(authorInfoDtoList.toArray().length <= limit);
        assertEquals(1, authorInfoDtoList.toArray().length);
        assertEquals(country, authorInfoDtoList.get(0).getCountry());
        assertTrue(authorInfoDtoList.get(0).getFullName().endsWith(surname));
    }

    @Test
    void getAll() throws Exception {
        String body = """
                {
                }
                """;
        MvcResult mvcResult = mockMvc.perform(get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorDetailsDto> authorDetailsDtoList = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorDetailsDto.class);
        assertEquals(3, authorDetailsDtoList.toArray().length);
    }

    @Test
    void searchPaginationTest() throws Exception {
        String country = "Ukraine";
        String body = """
          {
              "country": "%s",
              "pagination":
              {
                "offset": %d,
                "limit": %d
              }
          }
        """;
        MvcResult mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(country, 0, 2)))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList1 = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);

        mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(country, 0, 1)))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList2 = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);

        mvcResult = mockMvc.perform(get("/api/authors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.formatted(country, 1, 1)))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorInfoDto> authorInfoDtoList3 = ResponseParser.parseListResponse(objectMapper, mvcResult, AuthorInfoDto.class);
        authorInfoDtoList2.addAll(authorInfoDtoList3);

        //assert that [offset=0, limit=2] equals [offset=0, limit=1] + [offset=1, limit=1]
        assertIterableEquals(authorInfoDtoList1, authorInfoDtoList2);
    }

}