package currencyrateservice.service;

import currencyrateservice.domain.ExchangeRate;

public interface RateService {
    ExchangeRate findByCurrency(String currency);
}
