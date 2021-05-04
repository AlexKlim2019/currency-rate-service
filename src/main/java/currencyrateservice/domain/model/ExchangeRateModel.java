package currencyrateservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class ExchangeRateModel {

    private String baseCurrency;
    private String currency;
    private BigDecimal saleRate;
    private BigDecimal purchaseRate;
}
