package currencyrateservice.repository;

import currencyrateservice.domain.ExchangeRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("ExchangeRateRepository")
class ExchangeRateRepositoryTest {

    private static final String CURRENCY = "GBP";

    private static final String BASE_CURRENCY = "UAH";

    private static final String SALE_RATE_STRING = "15.7000000";

    private static final String PURCHASE_RATE_STRING = "15.3500000";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExchangeRateRepository repository;

    private ExchangeRate exchangeRate;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        exchangeRate = createExchangeRate();
    }

    @Test
    void shouldReturnExistingExchangeRateByCurrency() {
        ExchangeRate expected = entityManager.persistAndFlush(exchangeRate);
        ExchangeRate actual = repository.findByCurrency(CURRENCY).get();
        assertThat(actual).isEqualTo(expected);
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
