package currencyrateservice.service.impl;

import currencyrateservice.config.Constants.LogMessage;
import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.repository.ExchangeRateRepository;
import currencyrateservice.service.CurrencyDataService;
import currencyrateservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository repository;

    private final CurrencyDataService service;

    @Override
    public ExchangeRate findByCurrency(String currency) {
        log.info(LogMessage.IN_FIND_BY_CURRENCY, currency);

        Optional<ExchangeRate> rate = repository.findByCurrency(currency);
        if (rate.isEmpty()) {
            ExchangeRate newRate = createNewRate(currency);
            return updateOrPopulateRate(newRate);
        } else if (isOldRateFromDb(rate)) {
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

    private boolean isOldRateFromDb(Optional<ExchangeRate> rateDataFromDb) {
        LocalDate dbRateDate = rateDataFromDb.get().getUpdatedDate().toLocalDate();
        return dbRateDate.compareTo(LocalDate.now()) > 0;
    }
}
