package kafkamail;

import jakarta.mail.internet.MimeMessage;
import kafkamail.listener.MailReceivedListener;
import kafkamail.model.Mail;
import kafkamail.model.MailStatusCode;
import kafkamail.repository.MailRepository;
import kafkamail.servise.mail.registration.MailRegistrationService;
import kafkamail.servise.mail.sender.MailSenderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@SpringBootTest(classes = KafkaMailApplication.class)
@ExtendWith(SpringExtension.class)
@Testcontainers
class SendMailServiceTests {

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private JavaMailSender mailSender;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private MailRepository mailRepository;

    @Container
    private static MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:5.0.14"))
                    .withReuse(true)
                    .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1));

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @AfterEach
    public void cleanUpEach(){
        mailRepository.deleteAll();
    }

    @Test
    void sendMailSuccess() {

        Mail mail = Mail.builder()
                .from("from@gmail.com")
                .to("to@gmail.com")
                .subject("subject")
                .text("text")
                .build();

        doNothing().when(mailSender).send(any(MimeMessage.class));

        int dbRecordsBeforeMailSend = mailRepository.findAll().size();

        mailSenderService.send(mail);

        Mail savedMail = mailRepository.findAll().get(0);

        int dbRecordsAfterMailSend = mailRepository.findAll().size();
        int failedMailsCount = mailRepository.findByStatus(MailStatusCode.FAILED).size();
        int successMailsCount = mailRepository.findByStatus(MailStatusCode.SENT).size();

        verify(mailSender, times(1)).send(any(MimeMessage.class));

        assertEquals(0, dbRecordsBeforeMailSend);
        assertEquals(1, dbRecordsAfterMailSend);

        assertEquals(0, failedMailsCount);
        assertEquals(1, successMailsCount);

        assertNull(savedMail.getMailStatus().getMailError());

    }

    @Test
    void sendMailFailed() {

        Mail mail = Mail.builder()
                .from("from@gmail.com")
                .to("to@gmail.com")
                .subject("subject")
                .text("text")
                .build();

        doThrow(new MailSendException("Failed to send email")).when(mailSender).send(any(MimeMessage.class));

        int dbRecordsBeforeMailSend = mailRepository.findAll().size();

        mailSenderService.send(mail);

        Mail savedMail = mailRepository.findAll().get(0);

        int dbRecordsAfterMailSend = mailRepository.findAll().size();
        int failedMailsCount = mailRepository.findByStatus(MailStatusCode.FAILED).size();
        int successMailsCount = mailRepository.findByStatus(MailStatusCode.SENT).size();

        verify(mailSender, times(1)).send(any(MimeMessage.class));

        assertEquals(0, dbRecordsBeforeMailSend);
        assertEquals(1, dbRecordsAfterMailSend);

        assertEquals(1, failedMailsCount);
        assertEquals(0, successMailsCount);

        assertEquals(MailStatusCode.FAILED, savedMail.getMailStatus().getMailStatusCode());
        assertEquals(MailSendException.class.toString(), savedMail.getMailStatus().getMailError().getErrorClass());
        assertEquals("Failed to send email", savedMail.getMailStatus().getMailError().getErrorMessage());

    }

}