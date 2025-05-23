package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.config.ForexApiConfig;
import bg.softuni.mobilelele.model.entity.ExRate;
import bg.softuni.mobilelele.repository.ExRateRepository;
import bg.softuni.mobilelele.service.exception.ApiObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExRateServiceImplTest {

    private static final class TestRates {
        // SUD -> base
        // CUR1 -> 4
        // CUR2 -> 0.5

        private static final String BASE_CURRENCY = "SUD";

        private static final ExRate CUR1 = new ExRate()
                .setCurrency("CUR1").setRate(new BigDecimal("4"));

        private static final ExRate CUR2 = new ExRate()
                .setCurrency("CUR2").setRate(new BigDecimal("0.5"));
    }

    private ExRateServiceImpl toTest;

    @Mock(strictness = Mock.Strictness.LENIENT)
    private ExRateRepository mockRepository;

    @BeforeEach
    void setUp() {
        toTest = new ExRateServiceImpl(
                mockRepository,
                null,
                new ForexApiConfig().setBase(TestRates.BASE_CURRENCY),
                null
        );
    }

    // 1 SUD ->   4 CUR1
    // 1 SUD -> 0.5 CUR2

    @ParameterizedTest(name = "Converting {2} {0} to {1}. Expected {3}")
    @CsvSource(
            textBlock = """
                    SUD, CUR1, 1, 4.00
                    SUD, CUR1, 2, 8.00
                    SUD, SUD,  1, 1
                    CUR1,CUR2, 1, 0.12
                    CUR2,CUR1, 1, 8.00
                    LBD, LBD, 1, 1
                    """
    )
    void testConvert(String from,
                     String to,
                     BigDecimal amount,
                     BigDecimal expected) {

        initExRates();

        BigDecimal result = toTest.convert(from, to, amount);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testApiObjectNotFoundException() {
        Assertions.assertThrows(ApiObjectNotFoundException.class,
                () -> toTest.convert("NO_EXIST_1", "NOT_EXIST_2", BigDecimal.ONE)
        );
    }

    private void initExRates() {
        when(mockRepository.findByCurrency(TestRates.CUR1.getCurrency()))
                .thenReturn(Optional.of(TestRates.CUR1));
        when(mockRepository.findByCurrency(TestRates.CUR2.getCurrency()))
                .thenReturn(Optional.of(TestRates.CUR2));
    }

    @Test
    void testHasInitializedExRates() {
        when(mockRepository.count()).thenReturn(0L);
        Assertions.assertFalse(toTest.hasInitializedRates());

        when(mockRepository.count()).thenReturn(-5L);
        Assertions.assertFalse(toTest.hasInitializedRates());

        when(mockRepository.count()).thenReturn(6L);
        Assertions.assertTrue(toTest.hasInitializedRates());
    }
}
