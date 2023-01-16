package bookstore.dto.book;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDetailsDto {
    private int id;
    private String title;
    private int pages;
    private int year;
    private int authorId;
}
