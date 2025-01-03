package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Engine;

public record OfferSummaryDTO(Long id, String description, Integer mileage, Engine engineType, double price) {
    public OfferSummaryDTO(Long id, String description, Integer mileage, Engine engineType, double price) {
        this.id = id;
        this.description = description;
        this.mileage = mileage;
        this.engineType = engineType;
        this.price = price;
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

    public Engine engineType() {
        return this.engineType;
    }
}
