package kafkamail.repository;

import kafkamail.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailRepository extends MongoRepository<Mail, String>, CustomMailRepository {
}
