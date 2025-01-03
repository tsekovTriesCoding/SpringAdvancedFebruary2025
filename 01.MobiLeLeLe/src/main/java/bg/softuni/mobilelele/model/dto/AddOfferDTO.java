package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.EngineTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record AddOfferDTO(@NotNull(
        message = "{add.offer.description.not.null}"
) @Size(
        message = "{add.offer.description.length}",
        min = 5,
        max = 500
) String description, @NotNull @PositiveOrZero Integer mileage, @NotNull @PositiveOrZero Integer price,
                          @NotNull EngineTypeEnum engineType) {
    public AddOfferDTO(@NotNull(
            message = "{add.offer.description.not.null}"
    ) @Size(
            message = "{add.offer.description.length}",
            min = 5,
            max = 500
    ) String description, @NotNull @PositiveOrZero Integer mileage, @NotNull @PositiveOrZero Integer price, @NotNull EngineTypeEnum engineType) {
        this.description = description;
        this.mileage = mileage;
        this.price = price;
        this.engineType = engineType;
    }

    public static AddOfferDTO empty() {
        return new AddOfferDTO((String) null, (Integer) null, (Integer) null, (EngineTypeEnum) null);
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
