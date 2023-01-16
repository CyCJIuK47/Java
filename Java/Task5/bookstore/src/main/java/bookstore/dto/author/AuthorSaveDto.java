package bookstore.dto.author;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;


@Getter
@Jacksonized
@Builder
@AllArgsConstructor
@ToString
public class AuthorSaveDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "surname is required")
    private String surname;

    @NotBlank(message = "country is required")
    private String country;
}
