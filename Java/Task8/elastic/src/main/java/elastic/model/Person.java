package elastic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import elastic.utils.deserializers.MultiDateDeserializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "persons")
public class Person {

    @Id
    @JsonIgnore
    private String id;

    @Field(type = FieldType.Text)
    @JsonProperty("type_of_official")
    private String typeOfOfficial;

    @Field(type = FieldType.Text)
    @JsonProperty("type_of_official_en")
    private String typeOfOfficialEn;

    @Field(type = FieldType.Keyword)
    @JsonProperty("first_name")
    private String firstName;

    @Field(type = FieldType.Keyword)
    @JsonProperty("first_name_en")
    private String firstNameEn;

    @Field(type = FieldType.Keyword)
    @JsonProperty("last_name")
    private String lastName;

    @Field(type = FieldType.Keyword)
    @JsonProperty("last_name_en")
    private String lastNameEn;

    @Field(type = FieldType.Keyword)
    @JsonProperty("patronymic")
    private String patronymic;

    @Field(type = FieldType.Keyword)
    @JsonProperty("patronymic_en")
    private String patronymicEn;

    @Field(type = FieldType.Text)
    @JsonProperty("full_name")
    private String fullName;

    @Field(type = FieldType.Text)
    @JsonProperty("full_name_en")
    private String fullNameEn;

    @Field(type = FieldType.Text)
    @JsonProperty("also_known_as_uk")
    private String alsoKnownAsUk;

    @Field(type = FieldType.Text)
    @JsonProperty("also_known_as_en")
    private String alsoKnownAsEn;

    @Field(type = FieldType.Text)
    @JsonProperty("names")
    private String names;

    @Field(type = FieldType.Boolean)
    @JsonProperty("is_pep")
    private boolean isPep;

    @Field(type = FieldType.Boolean)
    @JsonProperty("died")
    private boolean isDead;

    @Field(type = FieldType.Keyword, index = false)
    @JsonProperty("photo")
    private String photo;

    @Field(type = FieldType.Date)
    @JsonProperty("date_of_birth")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date dateOfBirth;

    @Field(type = FieldType.Text)
    @JsonProperty("city_of_birth_uk")
    private String cityOfBirthUk;

    @Field(type = FieldType.Text)
    @JsonProperty("city_of_birth_en")
    private String cityOfBirthEn;

    @Field(type = FieldType.Keyword, index = false)
    @JsonProperty("url")
    private String url;

    @Field(type = FieldType.Text, index = false)
    @JsonProperty("wiki_uk")
    private String wikiUk;

    @Field(type = FieldType.Text, index = false)
    @JsonProperty("wiki_en")
    private String wikiEn;

    @Field(type = FieldType.Text)
    @JsonProperty("last_job_title")
    private String lastJobTitle;

    @Field(type = FieldType.Text)
    @JsonProperty("last_job_title_en")
    private String lastJobTitleEn;

    @Field(type = FieldType.Text)
    @JsonProperty("last_workplace_uk")
    @JsonAlias("last_workplace")
    private String lastWorkplaceUk;

    @Field(type = FieldType.Text)
    @JsonProperty("last_workplace_en")
    private String lastWorkPlaceEn;

    @Field(type = FieldType.Text)
    @JsonProperty("reason_of_termination")
    private String reasonOfTermination;

    @Field(type = FieldType.Text)
    @JsonProperty("reason_of_termination_en")
    private String reasonOfTerminationEn;

    @Field(type = FieldType.Date)
    @JsonProperty("termination_date_human")
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private Date terminationDateHuman;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_convictions_uk")
    private String reputationConvictionsUk;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_convictions_en")
    private String reputationConvictionsEn;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_manhunt_uk")
    private String reputationManhuntUk;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_manhunt_en")
    private String reputationManhuntEn;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_sanctions_uk")
    private String reputationSanctionsUk;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_sanctions_en")
    private String reputationSanctionsEn;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_assets_uk")
    private String reputationAssetsUk;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_assets_en")
    private String reputationAssetsEn;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_crimes_uk")
    private String reputationCrimesUk;

    @Field(type = FieldType.Text)
    @JsonProperty("reputation_crimes_en")
    private String reputationCrimesEn;

    @Field(type = FieldType.Object)
    @JsonProperty("related_persons")
    private List<RelatedPerson> relatedPersons;

    @Field(type = FieldType.Object)
    @JsonProperty("declarations")
    private List<Declaration> declarations;

    @Field(type = FieldType.Object)
    @JsonProperty("related_companies")
    private List<RelatedCompany> relatedCompanies;

    @Field(type = FieldType.Object)
    @JsonProperty("related_countries")
    private List<RelatedCompany> relatedCountries;

}
