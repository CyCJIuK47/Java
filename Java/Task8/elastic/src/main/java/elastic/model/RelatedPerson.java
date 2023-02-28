package elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import elastic.utils.deserializers.MultiDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatedPerson {

    @Field(type = FieldType.Text)
    @JsonProperty("relationship_type_en")
    private String relationTypeEn;

    @Field(type = FieldType.Text)
    @JsonProperty("relationship_type")
    private String relationType;

    @Field(type = FieldType.Text)
    @JsonProperty("person_en")
    private String personEn;

    @Field(type = FieldType.Text)
    @JsonProperty("person_uk")
    private String personUk;

    @Field(type = FieldType.Date)
    @JsonProperty("date_established")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateEstablished;

    @Field(type = FieldType.Date)
    @JsonProperty("date_confirmed")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateConfirmed;

    @Field(type = FieldType.Date)
    @JsonProperty("date_finished")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateFinished;

    @Field(type = FieldType.Boolean)
    @JsonProperty("is_pep")
    private boolean isPep;

}
