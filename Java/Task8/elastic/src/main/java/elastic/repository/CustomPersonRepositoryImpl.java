package elastic.repository;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationBuilders;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import elastic.model.NameCount;
import elastic.model.Person;
import elastic.model.PersonPartialInfo;
import elastic.utils.sourcefilters.ClassToSourceFilterConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;


@AllArgsConstructor
public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Override
    public List<PersonPartialInfo> findByFullName(String fullName) {

        String matchName = String.format("\"%s\"", fullName);

        CriteriaQuery query = new CriteriaQuery(
                new Criteria("fullNameEn").matchesAll(matchName)
                        .or(new Criteria("fullName").matchesAll(matchName))
                        .or(new Criteria("alsoKnownAsEn").matchesAll(matchName))
                        .or(new Criteria("alsoKnownAsUk").matchesAll(matchName))
                        .or(new Criteria("names").matchesAll(matchName)));

        query.addSourceFilter(ClassToSourceFilterConverter.convert(PersonPartialInfo.class));

        SearchHits<PersonPartialInfo> result = elasticsearchOperations.search(
                query,
                PersonPartialInfo.class,
                elasticsearchOperations.getIndexCoordinatesFor(Person.class)
        );

        return result.stream()
                .map(SearchHit::getContent)
                .toList();
    }

    @Override
    public List<NameCount> getTopTenPopularPepNames() {

        Aggregation aggregation = AggregationBuilders
                .terms()
                .field("firstName")
                .build()
                ._toAggregation();

        Query pepFilterQuery = QueryBuilders
                .termQuery("isPep", "true")
                ._toQuery();

        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(pepFilterQuery)
                .withMaxResults(0)
                .withAggregation(
                        "topNames",
                        aggregation
                )
                .build();

        SearchHits<Person> response = elasticsearchOperations.search(nativeQuery, Person.class);

        List<StringTermsBucket> aggregationResultList = ((ElasticsearchAggregations) response
                .getAggregations())
                .get("topNames")
                .aggregation()
                .getAggregate()
                .sterms()
                .buckets()
                .array();

        return aggregationResultList.stream()
                .map(x -> new NameCount(x.key().stringValue(), x.docCount()))
                .toList();

    }
}
