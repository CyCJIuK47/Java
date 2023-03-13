package kafkamail.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailError {
    private String errorClass;
    private String errorMessage;


    public MailError (Exception exception) {
        errorClass = exception.getClass().toString();
        errorMessage = exception.getMessage();
    }

}
