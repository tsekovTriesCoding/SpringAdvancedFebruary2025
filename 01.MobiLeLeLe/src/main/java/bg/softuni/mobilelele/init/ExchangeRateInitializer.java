package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class ExchangeRateInitializer implements CommandLineRunner {
    private final ExRateService exRateService;

    public ExchangeRateInitializer(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @Override
    public void run(String... args) throws Exception {
//        if (!this.exRateService.hasInitializedRates()) {
//            this.exRateService.updateRates(this.exRateService.fetchExRates());
//        }
    }
}
