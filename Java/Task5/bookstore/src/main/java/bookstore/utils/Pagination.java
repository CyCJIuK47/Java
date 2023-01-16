package bookstore.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int offset = 0;
    private int limit = 50;
}
