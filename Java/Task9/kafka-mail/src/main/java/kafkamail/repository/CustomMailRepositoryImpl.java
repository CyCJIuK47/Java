package kafkamail.repository;

import kafkamail.model.Mail;
import kafkamail.model.MailStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Repository
@RequiredArgsConstructor
public class CustomMailRepositoryImpl implements CustomMailRepository {

    private final MongoTemplate mongoTemplate;


    @Override
    public List<Mail> findByStatus(MailStatusCode mailStatusCode) {

        Query mongoQuery = new Query();
        mongoQuery.addCriteria(where("mailStatus.mailStatusCode").is(mailStatusCode));

        return mongoTemplate.find(mongoQuery, Mail.class);
    }
}
