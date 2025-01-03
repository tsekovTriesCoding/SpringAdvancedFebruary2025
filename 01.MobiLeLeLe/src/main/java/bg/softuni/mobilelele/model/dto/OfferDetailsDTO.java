package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Engine;
import java.util.List;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              Double price,
                              Engine engine,
                              List<String> allCurrencies,
                              double offerPrice) {
    public OfferDetailsDTO(Long id, String description, Integer mileage, Double price, Engine engine, List<String> allCurrencies, double offerPrice) {
        this.id = id;
        this.description = description;
        this.mileage = mileage;
        this.price = price;
        this.engine = engine;
        this.allCurrencies = allCurrencies;
        this.offerPrice = offerPrice;
    }

    public Long id() {
        return this.id;
    }

    public String description() {
        return this.description;
    }

    public Integer mileage() {
        return this.mileage;
    }

    public Double price() {
        return this.price;
    }

    public Engine engine() {
        return this.engine;
    }

    public List<String> allCurrencies() {
        return this.allCurrencies;
    }
}
