package currencyrateservice.service.impl;

import currencyrateservice.domain.Rate;
import currencyrateservice.repository.RateRepository;
import currencyrateservice.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RateServiceImpl implements RateService {

    private final RateRepository repository;

    @Override
    public Rate findByCurrency(String currency) {
        return repository.findByCurrency(currency);
    }
}
