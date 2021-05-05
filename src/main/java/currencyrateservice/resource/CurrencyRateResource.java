package currencyrateservice.resource;

import currencyrateservice.config.Constants;
import currencyrateservice.config.Constants.LogMessage;
import currencyrateservice.config.Constants.URL;
import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.exception.InvalidInputException;
import currencyrateservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(URL.CURRENCY_RATE_BASE_URL)
public class CurrencyRateResource {

    private final ExchangeRateService service;

    @GetMapping("/{currency}")
    public ResponseEntity<ExchangeRate> getCurrencyRate(@PathVariable String currency) {
        log.debug(LogMessage.IN_GET_CURRENCY_RATE, currency);

        currency = currency.trim();
        if (currency.isEmpty()) {
            throw new InvalidInputException(Constants.ErrorMessage.INVALID_INPUT_EXCEPTION);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            service.findByCurrency(currency));
    }
}
