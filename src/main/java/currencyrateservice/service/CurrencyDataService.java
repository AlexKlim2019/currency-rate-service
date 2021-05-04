package currencyrateservice.service;

import currencyrateservice.domain.ExchangeRate;

public interface CurrencyDataService {
    ExchangeRate getRateByCurrency(String currency);
}
