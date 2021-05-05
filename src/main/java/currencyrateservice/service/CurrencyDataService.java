package currencyrateservice.service;

import currencyrateservice.domain.model.ExchangeRateModel;

public interface CurrencyDataService {
    ExchangeRateModel getRateModelByCurrency(String currency);
}
