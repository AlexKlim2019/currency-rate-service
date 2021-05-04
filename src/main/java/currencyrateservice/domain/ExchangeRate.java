package currencyrateservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rates")
public class ExchangeRate {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String currency;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "sale_rate")
    private BigDecimal saleRate;

    @Column(name = "purchase_rate")
    private BigDecimal purchaseRate;

    @JsonIgnore
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
