package kafkamail.dto.converters;

import kafkamail.dto.MailDto;
import kafkamail.model.Mail;
import kafkamail.model.MailStatus;
import kafkamail.model.MailStatusCode;


public class MailDtoConverter {

    public static Mail toMail(MailDto mailDto) {
        return Mail.builder()
                .to(mailDto.getTo())
                .from(mailDto.getFrom())
                .subject(mailDto.getSubject())
                .text(mailDto.getText())
                .mailStatus(new MailStatus(MailStatusCode.NEW))
                .build();
    }
}
