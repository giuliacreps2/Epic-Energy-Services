package team5.Epic_Energy_Services.tools;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team5.Epic_Energy_Services.entities.B2bClient;


@Slf4j
@Component
public class EmailSender {
    private final String domainName;
    private final String apiKey;

    public EmailSender(@Value("${mailgun.domainName}") String domainName, @Value("${mailgun.apiKey}") String apiKey) {
        this.domainName = domainName;
        this.apiKey = apiKey;
    }

    public void sendRegistrationEmail(B2bClient recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "dattolafrancescoo@gmail.com")
                .queryString("to", recipient.getContactEmail())
                .queryString("subject", "Welcome!")
                .queryString("text", "Congratulations" + recipient.getContactName() + " Your subscription is gone successfully!")
                .asJson();

        log.info(String.valueOf(response.getBody()));
    }


}
