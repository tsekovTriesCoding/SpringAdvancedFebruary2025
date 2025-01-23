package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
@Profile("!test")
@ConditionalOnProperty(name = "forex.init-ex-rates", havingValue = "true")
public class ExchangeRateInitializer implements CommandLineRunner {

    private final ExRateService exRateService;

    public ExchangeRateInitializer(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!exRateService.hasInitializedRates()) {
            exRateService.updateRates(
                    exRateService.fetchExRates()
            );
        }
    }
}
