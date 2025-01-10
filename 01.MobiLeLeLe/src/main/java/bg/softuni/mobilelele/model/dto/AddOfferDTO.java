package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.EngineTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record AddOfferDTO(
        @NotNull(message = "{add.offer.description.length}")
        @Size(message = "{add.offer.description.length}",
                min = 5,
                max = 500) String description,//not necessarily from message source
        @NotNull @PositiveOrZero Integer mileage,
        @NotNull @PositiveOrZero Integer price,
        @NotNull EngineTypeEnum engineType
) {

    public static AddOfferDTO empty() {
        return new AddOfferDTO(null, null, null, null);
    }

    public @NotNull(
            message = "{add.offer.description.not.null}"
    ) @Size(
            message = "{add.offer.description.length}",
            min = 5,
            max = 500
    ) String description() {
        return this.description;
    }

    public @NotNull @PositiveOrZero Integer mileage() {
        return this.mileage;
    }

    public @NotNull @PositiveOrZero Integer price() {
        return this.price;
    }

    public @NotNull EngineTypeEnum engineType() {
        return this.engineType;
    }
}
