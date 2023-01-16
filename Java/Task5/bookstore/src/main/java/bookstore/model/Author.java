package bookstore.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author {
    private int id;
    private String name;
    private String surname;
    private String country;
}
