package bg.softuni.mobileleleoffers.model.dto;

import bg.softuni.mobileleleoffers.model.enums.EngineTypeEnum;

public record AddOfferDTO(
        String description,
        Integer mileage,
        Integer price,
        EngineTypeEnum engineType) {
}
