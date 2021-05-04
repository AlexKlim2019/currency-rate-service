package currencyrateservice.service.impl;

import currencyrateservice.domain.model.BankDataModel;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.service.CurrencyDataService;
import currencyrateservice.service.JsonParser;
import currencyrateservice.service.RestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PBCurrencyDataService implements CurrencyDataService {

    private final RestHandler handler;

    private final JsonParser parser;

    @Override
    public ExchangeRateModel getRateModelByCurrency(String currency) {
        String currencyDateJson = handler.doGet().getBody();
        List<ExchangeRateModel> rates = receiveCurrencyDataFromJson(currencyDateJson);

        return rates.stream()
                .filter(rateModel -> rateModel.getCurrency().equals(currency))
                .findAny()
                .orElseThrow(null);
    }

    private List<ExchangeRateModel> receiveCurrencyDataFromJson(String json) {
        BankDataModel model = parser.parseJson(json, BankDataModel.class);
        return model.getExchangeRate();
    }
}
