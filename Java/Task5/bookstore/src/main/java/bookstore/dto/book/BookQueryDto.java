package bookstore.dto.book;

import bookstore.utils.Pagination;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookQueryDto {
    private String title;
    private int authorId;
    @NotNull(message = "Setup pagination parameters: offset and login")
    private Pagination pagination;
}
