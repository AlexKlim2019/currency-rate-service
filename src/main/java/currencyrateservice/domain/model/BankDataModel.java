package currencyrateservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDataModel {

    private String bank;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private List<ExchangeRateModel> exchangeRate;
}
