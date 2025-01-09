package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.EngineTypeEnum;

public record OfferSummaryDTO(Long id, String description, Integer mileage, EngineTypeEnum engineType, double price) {
    public OfferSummaryDTO(Long id, String description, Integer mileage, EngineTypeEnum engineType, double price) {
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

    public EngineTypeEnum engineType() {
        return this.engineType;
    }
}
