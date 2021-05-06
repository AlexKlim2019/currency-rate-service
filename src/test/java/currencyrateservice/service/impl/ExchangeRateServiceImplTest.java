package currencyrateservice.service.impl;

import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.repository.ExchangeRateRepository;
import currencyrateservice.service.CurrencyDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExchangeRateServiceImpl")
class ExchangeRateServiceImplTest {

    private static final String CURRENCY = "GBP";

    private static final String BASE_CURRENCY = "UAH";

    private static final String SALE_RATE_STRING = "15.7000000";

    private static final String PURCHASE_RATE_STRING = "15.3500000";

    @Mock
    private ExchangeRateRepository repositoryMock;

    @Mock
    private CurrencyDataService serviceMock;

    private ExchangeRateServiceImpl exchangeRateService;

    @BeforeEach
    void setUp() {
        exchangeRateService =
                new ExchangeRateServiceImpl(repositoryMock, serviceMock);
    }

    @Test
    void shouldReturnExchangeRateByCurrencyFromDb() {
        ExchangeRate expected = createExchangeRate();

        when(repositoryMock.findByCurrency(anyString()))
                .thenReturn(Optional.of(expected));

        ExchangeRate actual = exchangeRateService.findByCurrency(CURRENCY);

        assertThat(actual).isEqualTo(expected);
        verify(repositoryMock, times(1)).findByCurrency(anyString());

    }

    private ExchangeRate createExchangeRate() {
        BigDecimal saleRate = new BigDecimal(SALE_RATE_STRING);
        BigDecimal purchaseRate = new BigDecimal(PURCHASE_RATE_STRING);
        return ExchangeRate.builder()
                .baseCurrency(BASE_CURRENCY)
                .currency(CURRENCY)
                .saleRate(saleRate)
                .purchaseRate(purchaseRate)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
