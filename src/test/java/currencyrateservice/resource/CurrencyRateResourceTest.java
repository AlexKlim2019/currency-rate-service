package currencyrateservice.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.service.ExchangeRateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CurrencyRateResource")
class CurrencyRateResourceTest {

    private static final String USERNAME = "freeman@gmail.com";

    private static final String CURRENCY = "GBP";

    private static final String BASE_CURRENCY = "UAH";

    private static final String SALE_RATE_STRING = "15.7000000";

    private static final String PURCHASE_RATE_STRING = "15.3500000";

    private static final String BASE_URL = "/v1.0/currency/rate/";

    private static final String URL = BASE_URL.concat(CURRENCY);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ExchangeRateService serviceMock;

    @Test
    @WithUserDetails(USERNAME)
    void shouldReturnResponseEntity() throws Exception {
        ExchangeRate exchangeRate = createExchangeRate();
        String expected = mapper.writeValueAsString(exchangeRate);
        when(serviceMock.findByCurrency(CURRENCY)).thenReturn(exchangeRate);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(expected));

        verify(serviceMock, times(1)).findByCurrency(anyString());
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
