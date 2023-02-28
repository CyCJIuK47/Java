package elastic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class RelatedCompany {

    @Field(type = FieldType.Text)
    @JsonProperty("relationship_type_en")
    private String relationTypeEn;

    @Field(type = FieldType.Text)
    @JsonAlias("relationship_type")
    @JsonProperty("relationship_type_uk")
    private String relationTypeUk;

    @Field(type = FieldType.Text)
    @JsonProperty("to_country_en")
    private String toCountryEn;

    @Field(type = FieldType.Text)
    @JsonProperty("to_country_uk")
    private String toCountryUk;


    @Field(type = FieldType.Text)
    @JsonProperty("to_company_en")
    private String toCompanyEn;

    @Field(type = FieldType.Text)
    @JsonProperty("to_company_uk")
    private String toCompanyUk;

    @Field(type = FieldType.Text)
    @JsonProperty("to_company_short_en")
    private String toCompanyShortEn;

    @Field(type = FieldType.Text)
    @JsonProperty("to_company_short_uk")
    private String toCompanyShortUk;

    @Field(type = FieldType.Date)
    @JsonProperty("to_company_founded")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date toCompanyFounded;

    @Field(type = FieldType.Keyword)
    @JsonProperty("to_company_edrpou")
    private String toCompanyEdrpou;

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

    @Field(type = FieldType.Long)
    @JsonProperty("share")
    private long share;

    @Field(type = FieldType.Boolean)
    @JsonProperty("to_company_is_state")
    private boolean toCompanyIsState;

}
