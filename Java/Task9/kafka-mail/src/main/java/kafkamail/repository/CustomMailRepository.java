package kafkamail.repository;

import kafkamail.model.Mail;
import kafkamail.model.MailStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomMailRepository {
    List<Mail> findByStatus(MailStatusCode mailStatusCode);
}
