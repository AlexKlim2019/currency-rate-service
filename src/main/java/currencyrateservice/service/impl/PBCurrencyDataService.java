package currencyrateservice.service.impl;

import currencyrateservice.config.Constants.LogMessage;
import currencyrateservice.config.Constants.ErrorMessage;
import currencyrateservice.domain.model.BankDataModel;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.exception.ResourceNotFoundException;
import currencyrateservice.service.CurrencyDataService;
import currencyrateservice.service.JsonParser;
import currencyrateservice.service.RestHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PBCurrencyDataService implements CurrencyDataService {

    private final RestHandler handler;

    private final JsonParser parser;

    @Override
    public ExchangeRateModel getRateModelByCurrency(String currency){
        log.info(LogMessage.IN_GET_RATE_MODEL_BY_CURRENCY, currency);

        String currencyDateJson = handler.doGet().getBody();
        List<ExchangeRateModel> rates = receiveCurrencyDataFromJson(currencyDateJson);

        return rates.stream()
                .filter(rateModel -> rateModel.getCurrency() != null &&
                                     rateModel.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessage.CURRENCY_NOT_FOUND_EXCEPTION, currency)));
    }

    private List<ExchangeRateModel> receiveCurrencyDataFromJson(String json) {
        BankDataModel model = parser.parseJson(json, BankDataModel.class);
        return model.getExchangeRate();
    }
}
