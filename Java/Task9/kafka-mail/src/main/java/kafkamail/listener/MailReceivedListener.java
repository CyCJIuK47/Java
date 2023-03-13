package kafkamail.listener;

import kafkamail.model.Mail;
import kafkamail.servise.mail.sender.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MailReceivedListener {

    private final MailSenderService mailSenderService;

    @KafkaListener(topics = "${kafka.topic.mailReceived}")
    public void mailReceived(Mail mail) {
        mailSenderService.send(mail);
    }

}
