package kafkamail.servise.mail.dbscan;

import kafkamail.model.Mail;
import kafkamail.model.MailStatusCode;
import kafkamail.repository.MailRepository;
import kafkamail.servise.mail.sender.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FailedMailsScanServiceImpl implements FailedMailsScanService {


    private final MailSenderService mailSenderService;

    private final MailRepository mailRepository;

    @Override
    @Scheduled(fixedRateString = "${spring.scheduling.dbscan.rate.ms}")
    public void scan() {
        log.info("Scheduled DatabaseScan started");
        List<Mail> mails = mailRepository.findByStatus(MailStatusCode.FAILED);
        log.info("Scheduled DatabaseScan found %d failed mails".formatted(mails.size()));
        mails.forEach(mailSenderService::send);
    }
}
