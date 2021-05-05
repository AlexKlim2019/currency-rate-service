package currencyrateservice.service.impl;

import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.repository.ExchangeRateRepository;
import currencyrateservice.service.CurrencyDataService;
import currencyrateservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository repository;

    private final CurrencyDataService service;

    @Override
    public ExchangeRate findByCurrency(String currency) {
        //Log
        Optional<ExchangeRate> rate = repository.findByCurrency(currency);
        LocalDate now = LocalDate.now().plusWeeks(1); // delete after test
        if (rate.isEmpty()) {
            ExchangeRate newRate = createNewRate(currency);
            return updateOrPopulateRate(newRate);
        } else if (now.compareTo(LocalDate.now()) > 0) {
            return updateOrPopulateRate(rate.get());
        }
        return rate.get();
    }

    private ExchangeRate updateOrPopulateRate(ExchangeRate rawRate) {
            ExchangeRateModel rateModel = service.getRateModelByCurrency(rawRate.getCurrency());
            rawRate.setSaleRate(rateModel.getSaleRate());
            rawRate.setPurchaseRate(rateModel.getPurchaseRate());
            rawRate.setBaseCurrency(rateModel.getBaseCurrency());
            return repository.saveAndFlush(rawRate);
    }

    private ExchangeRate createNewRate(String currency) {
        return ExchangeRate.builder()
                .currency(currency)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
