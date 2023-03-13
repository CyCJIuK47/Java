package kafkamail.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldNameConstants
@Document("mails")
public class Mail {
    @Id
    private String id;
    private String from;
    private String to;
    private String subject;
    private String text;
    private MailStatus mailStatus;
}
