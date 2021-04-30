package currencyrateservice.resource;

import currencyrateservice.config.ApiVersion;
import currencyrateservice.domain.Rate;
import currencyrateservice.repository.RateRepository;
import currencyrateservice.service.RateService;
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

    private final RateService service;

    @GetMapping("/{currency}")
    public ResponseEntity<Rate> getCurrencyRate(@PathVariable String currency) {
        return ResponseEntity.status(HttpStatus.OK).body(
            service.findByCurrency(currency));
    }
}
