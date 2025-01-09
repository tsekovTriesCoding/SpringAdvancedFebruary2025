package bg.softuni.mobilelele.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean(name = "genericRestClient")
    public RestClient genericRestClient() {
        return RestClient.create();
    }

    @Bean(name = "offersRestClient")
    public RestClient offersRestClient(OfferApiConfig offerApiConfig) {
        return RestClient
                .builder()
                .baseUrl(offerApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
