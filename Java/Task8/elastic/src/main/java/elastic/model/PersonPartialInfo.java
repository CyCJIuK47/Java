package elastic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonPartialInfo {

    private String typeOfOfficial;
    private String firstName;
    private String fullNameEn;
    private boolean isPep;
    private boolean isDead;
    private String cityOfBirthUk;
    private String cityOfBirthEn;

}
