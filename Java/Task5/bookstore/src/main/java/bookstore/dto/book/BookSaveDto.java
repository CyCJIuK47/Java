package bookstore.dto.book;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Getter
@Builder
@Jacksonized
public class BookSaveDto {

    @NotBlank(message = "title is required")
    private String title;

    @Min(value = 1, message = "pages must be greater than 1")
    private int pages;

    @Min(value = 1000, message = "year must be greater than 1000")
    private int year;

    @Min(value = 1, message = "authorId must be greater than 0")
    private int authorId;
}
