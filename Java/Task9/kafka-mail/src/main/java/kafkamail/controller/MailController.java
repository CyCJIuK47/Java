package kafkamail.controller;

import kafkamail.dto.AcknowledgeDto;
import kafkamail.dto.MailDto;
import kafkamail.servise.mail.registration.MailRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mails")
public class MailController {

    @Autowired
    private MailRegistrationService mailRegistrationService;

    @PostMapping("/send")
    public AcknowledgeDto sendMail(@RequestBody MailDto mailDto) {
        mailRegistrationService.register(mailDto);
        return new AcknowledgeDto("Mail registered");
    }

}
