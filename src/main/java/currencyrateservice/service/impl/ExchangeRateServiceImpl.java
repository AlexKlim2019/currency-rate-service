package currencyrateservice.service.impl;

import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.domain.model.ExchangeRateModel;
import currencyrateservice.repository.ExchangeRateRepository;
import currencyrateservice.service.CurrencyDataService;
import currencyrateservice.service.ExchangeRateService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository repository;

    private final CurrencyDataService service;

    @Override
    public ExchangeRate findByCurrency(String currency) {
        ExchangeRate rate = repository.findByCurrency(currency);
        if (rate.getUpdatedDate().toLocalDate().compareTo(LocalDate.now()) < 0) {
            try {
                ExchangeRateModel rateModel = service.getRateModelByCurrency(currency);
                rate.setSaleRate(rateModel.getSaleRate());
                rate.setPurchaseRate(rateModel.getPurchaseRate());
                return repository.saveAndFlush(rate);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return repository.findByCurrency(currency);
    }
}
