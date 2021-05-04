package currencyrateservice.service.impl;

import currencyrateservice.domain.ExchangeRate;
import currencyrateservice.repository.ExchangeRateRepository;
import currencyrateservice.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RateServiceImpl implements RateService {

    private final ExchangeRateRepository repository;

    @Override
    public ExchangeRate findByCurrency(String currency) {
        return repository.findByCurrency(currency);
    }
}
