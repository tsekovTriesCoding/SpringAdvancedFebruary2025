package bg.softuni.mobilelele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRate extends BaseEntity {
    private String currency;
    private BigDecimal rate;

    public ExRate() {
    }

    public String getCurrency() {
        return currency;
    }

    public ExRate setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExRate setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public String toString() {
        return "ExRate{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
