package currencyrateservice.service.impl;

import currencyrateservice.service.RestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RestHandlerImp")
class RestHandlerImpTest {

    private static final String RESPONSE_BODY = "Response body";

    private static final String EXTERNAL_URL = "https://api.privatbank.ua/p24api/exchange_rates";

    @Mock
    private RestTemplate restMock;

    private RestHandler restHandler;

    @BeforeEach
    void setUp() {
        restHandler = new RestHandlerImpl(EXTERNAL_URL, restMock);
    }

    @Test
    void shouldReturnResponseEntityIfGetWithParamsSucceeds() {
        ResponseEntity<String> expected = new ResponseEntity<>(RESPONSE_BODY, HttpStatus.OK);

        when(restMock.exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(expected);

        ResponseEntity<String> result = restHandler.doGet();

        assertThat(result).isEqualTo(expected);
        verify(restMock, times(1))
                .exchange(any(URI.class),
                    eq(HttpMethod.GET),
                    any(HttpEntity.class),
                    eq(String.class));
    }
}
