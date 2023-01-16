package bookstore.dto.author;

import bookstore.utils.Pagination;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorQueryDto {
    private String surname;
    private String country;
    @NotNull(message = "Setup pagination parameters: offset and login")
    private Pagination pagination;
}
