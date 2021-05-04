package currencyrateservice.service;

import currencyrateservice.domain.model.ExchangeRateModel;
import javassist.NotFoundException;

public interface CurrencyDataService {
    ExchangeRateModel getRateModelByCurrency(String currency) throws NotFoundException;
}
