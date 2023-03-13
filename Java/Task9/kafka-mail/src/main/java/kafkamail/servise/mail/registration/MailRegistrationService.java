package kafkamail.servise.mail.registration;

import kafkamail.dto.MailDto;


public interface MailRegistrationService {
    public void register(MailDto mailDto);
}
