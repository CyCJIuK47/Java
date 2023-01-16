package bookstore.dto.book;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@EqualsAndHashCode
@Jacksonized
public class BookInfoDto {
    private int id;
    private String title;
    private int authorId;
}
