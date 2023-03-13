package kafkamail.servise.mail.registration;

import kafkamail.dto.MailDto;
import kafkamail.dto.converters.MailDtoConverter;
import kafkamail.model.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailRegistrationServiceImpl implements MailRegistrationService {

    @Value("${kafka.topic.mailReceived}")
    private String mailReceivedTopic;

    private final KafkaOperations<String, Mail> kafkaOperations;

    @Override
    public void register(MailDto mailDto) {
        Mail mail = MailDtoConverter.toMail(mailDto);
        kafkaOperations.send(mailReceivedTopic, mail.getFrom(), mail);

    }
}
