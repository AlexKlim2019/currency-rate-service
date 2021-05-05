package currencyrateservice.resource;

import currencyrateservice.config.ApiVersion;
import currencyrateservice.config.Constants;
import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.exception.InvalidInputException;
import currencyrateservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static currencyrateservice.resource.CurrencyRateResource.BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(BASE_URL)
public class CurrencyRateResource {
    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/currency/rate";

    private final ExchangeRateService service;

    @GetMapping("/{currency}")
    public ResponseEntity<ExchangeRate> getCurrencyRate(@PathVariable String currency) {
        //log
        currency = currency.trim();
        if (currency.isEmpty()) {
            throw new InvalidInputException(Constants.ErrorMessage.INVALID_INPUT_EXCEPTION);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            service.findByCurrency(currency));
    }
}
