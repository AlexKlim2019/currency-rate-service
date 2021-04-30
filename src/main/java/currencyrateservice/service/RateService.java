package currencyrateservice.service;

import currencyrateservice.domain.Rate;

public interface RateService {
    Rate findByCurrency(String currency);
}
