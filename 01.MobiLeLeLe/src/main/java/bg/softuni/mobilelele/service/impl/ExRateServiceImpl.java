package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.config.ForexApiConfig;
import bg.softuni.mobilelele.model.dto.ExRatesDTO;
import bg.softuni.mobilelele.model.entity.ExRate;
import bg.softuni.mobilelele.repository.ExRateRepository;
import bg.softuni.mobilelele.service.ExRateService;
import bg.softuni.mobilelele.service.exception.ApiObjectNotFoundException;
import bg.softuni.mobilelele.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ExRateServiceImpl implements ExRateService {
    private final Logger LOGGER = LoggerFactory.getLogger(ExRateServiceImpl.class);
    private final ExRateRepository exRateRepository;
    private final RestClient restClient;
    private final ForexApiConfig forexApiConfig;

    public ExRateServiceImpl(ExRateRepository exRateRepository,
                             @Qualifier("genericRestClient") RestClient restClient,
                             ForexApiConfig forexApiConfig) {
        this.exRateRepository = exRateRepository;
        this.restClient = restClient;
        this.forexApiConfig = forexApiConfig;
    }

    @Override
    public List<String> allSupportedCurrencies() {
        return this.exRateRepository.findAll()
                .stream()
                .map(ExRate::getCurrency)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasInitializedRates() {
        return this.exRateRepository.count() > 0;
    }

    @Override
    public ExRatesDTO fetchExRates() {
        return this.restClient
                .get()
                .uri(this.forexApiConfig.getUrl(), this.forexApiConfig.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ExRatesDTO.class);
    }

    @Override
    public void updateRates(ExRatesDTO exRatesDTO) {
        this.LOGGER.info("Updating {} rates.", exRatesDTO.rates().size());

        if (!this.forexApiConfig.getBase().equals(exRatesDTO.base())) {
            throw new IllegalArgumentException("The exchange rates that should be updated are not based on "
                    + this.forexApiConfig.getBase() + "but rather on " + exRatesDTO.rates());
        }

        exRatesDTO.rates().forEach((currency, rate) -> {
            var exRate = this.exRateRepository.findByCurrency(currency)
                    .orElseGet(() -> new ExRate().setCurrency(currency));

            exRate.setRate(rate);
            this.exRateRepository.save(exRate);
        });
    }

    @Override
    public Optional<BigDecimal> findExRate(String from, String to) {
        if (Objects.equals(from, to)) {
            return Optional.of(BigDecimal.ONE);
        }

        //USD/BGN = x
        //USD/EUR = y

        //USD = x * BGN
        //USD = y * EUR

        //EUR/BGN = x/y

        Optional<BigDecimal> fromOpt = forexApiConfig.getBase().equals(from) ?
                Optional.of(BigDecimal.ONE) :
                exRateRepository.findByCurrency(from).map(ExRate::getRate);

        Optional<BigDecimal> toOpt = forexApiConfig.getBase().equals(to) ?
                Optional.of(BigDecimal.ONE) :
                exRateRepository.findByCurrency(to).map(ExRate::getRate);

        if (fromOpt.isEmpty() || toOpt.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(toOpt.get().divide(fromOpt.get(), 2, RoundingMode.HALF_DOWN));
    }

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        return this.findExRate(from, to)
                .orElseThrow(() -> new ApiObjectNotFoundException("Conversion from " + from + " to " + to + " not possible!", from + "~" + to))
                .multiply(amount);
    }
}
