package bookstore.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    private int id;
    private String title;
    private int pages;
    private int year;
    private int authorId;
}
