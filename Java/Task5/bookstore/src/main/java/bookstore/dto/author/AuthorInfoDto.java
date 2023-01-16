package bookstore.dto.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Builder
@EqualsAndHashCode
@Jacksonized
public class AuthorInfoDto {
    private int id;
    private String fullName;
    private String country;
}
