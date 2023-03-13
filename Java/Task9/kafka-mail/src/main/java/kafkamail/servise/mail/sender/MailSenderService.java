package kafkamail.servise.mail.sender;

import kafkamail.model.Mail;


public interface MailSenderService {

    public void send(Mail mail);
}
