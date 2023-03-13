package kafkamail.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class MailStatus {

    @NonNull
    private MailStatusCode mailStatusCode;

    private MailError mailError;
}
