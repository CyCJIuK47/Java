package bookstore.dto.author;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorDetailsDto {
    private int id;
    private String name;
    private String surname;
    private String country;
}
