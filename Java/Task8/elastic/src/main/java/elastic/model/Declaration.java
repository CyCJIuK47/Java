package elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {

    @Field(type = FieldType.Text)
    @JsonProperty("position_en")
    private String positionEn;

    @Field(type = FieldType.Text)
    @JsonProperty("position_uk")
    private String positionUk;

    @Field(type = FieldType.Text)
    @JsonProperty("office_en")
    private String officeEn;

    @Field(type = FieldType.Text)
    @JsonProperty("office_uk")
    private String officeUk;

    @Field(type = FieldType.Text)
    @JsonProperty("region_en")
    private String regionEn;

    @Field(type = FieldType.Text)
    @JsonProperty("region_uk")
    private String regionUk;

    @Field(type = FieldType.Long)
    @JsonProperty("income")
    private long income;

    @Field(type = FieldType.Long)
    @JsonProperty("family_income")
    private long familyIncome;

    @Field(type = FieldType.Long)
    @JsonProperty("year")
    private Long year;

    @Field(type = FieldType.Keyword, index = false)
    @JsonProperty("url")
    private String url;
}
