package currencyrateservice.service;

import currencyrateservice.domain.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate findByCurrency(String currency);
}
