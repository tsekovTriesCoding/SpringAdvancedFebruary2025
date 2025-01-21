package bg.softuni.mobileleleoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
public class MobiLeLeLeOffersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobiLeLeLeOffersApplication.class, args);
    }

}
