package kafkamail.servise.mail.sender;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import kafkamail.model.Mail;
import kafkamail.model.MailError;
import kafkamail.model.MailStatus;
import kafkamail.model.MailStatusCode;
import kafkamail.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@ConditionalOnProperty(value = "spring.scheduling.enabled", havingValue = "true")
public class MailSenderServiceImpl implements MailSenderService {

    private JavaMailSender mailSender;

    @Autowired
    private MailRepository mailRepository;


    @Override
    public void send(Mail mail) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(new InternetAddress(mail.getFrom(), mail.getFrom()));
            messageHelper.setTo(mail.getTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());

            mailSender.send(mimeMessage);

            mail.setMailStatus(new MailStatus(MailStatusCode.SENT));
            mailRepository.save(mail);

        } catch (Exception e) {
            mail.setMailStatus(new MailStatus(MailStatusCode.FAILED, new MailError(e)));
            mailRepository.save(mail);
        }

    }
}
