package currencyrateservice.service.impl;

import currencyrateservice.domain.model.BankDataModel;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.service.JsonParser;
import currencyrateservice.service.RestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PBCurrencyDataService")
class PBCurrencyDataServiceTest {

    private static final String BASE_CURRENCY = "baseCurrency";

    private static final String FIRST_CURRENCY = "firstCurrency";

    private static final String SECOND_CURRENCY = "secondCurrency";

    private static final BigDecimal RATE = new BigDecimal("1");

    private static final String JSON = "json";

    @Mock
    private RestHandler handlerMock;

    @Mock
    private JsonParser parserMock;

    private PBCurrencyDataService service;

    @BeforeEach
    void setUp() {
        service = new PBCurrencyDataService(handlerMock, parserMock);
    }

    @Test
    void shouldReturnExchangeRateModelByCurrency() {
        ExchangeRateModel firstRateModel =
                new ExchangeRateModel(BASE_CURRENCY, FIRST_CURRENCY, RATE, RATE);
        ExchangeRateModel secondRateModel =
                new ExchangeRateModel(BASE_CURRENCY, SECOND_CURRENCY, RATE, RATE);
        BankDataModel bankDataModel = BankDataModel.builder()
                .exchangeRate(List.of(firstRateModel, secondRateModel))
                .build();

        when(handlerMock.doGet()).thenReturn(new ResponseEntity<>(JSON, HttpStatus.OK));
        when(parserMock.parseJson(anyString(), eq(BankDataModel.class)))
                .thenReturn(bankDataModel);

        ExchangeRateModel expected = firstRateModel;
        ExchangeRateModel actual = service.getRateModelByCurrency(FIRST_CURRENCY);

        assertThat(actual).isEqualTo(expected);
        verify(parserMock, times(1))
                .parseJson(anyString(), eq(BankDataModel.class));
        verify(handlerMock, times(1)).doGet();
    }
}
