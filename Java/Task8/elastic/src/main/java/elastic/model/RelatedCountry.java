package elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import elastic.utils.deserializers.MultiDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class RelatedCountry {

    @Field(type = FieldType.Keyword)
    @JsonProperty("relationship_type")
    private String relationshipType;

    @Field(type = FieldType.Text)
    @JsonProperty("to_country_en")
    private String toCountryEn;

    @Field(type = FieldType.Text)
    @JsonProperty("to_country_uk")
    private String toCountryUk;

    @Field(type = FieldType.Date)
    @JsonProperty("date_established")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateEstablished;

    @Field(type = FieldType.Date)
    @JsonProperty("date_finished")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateFinished;

    @Field(type = FieldType.Date)
    @JsonProperty("date_confirmed")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateConfirmed;

}
