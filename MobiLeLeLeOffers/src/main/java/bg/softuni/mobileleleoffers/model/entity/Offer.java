package bg.softuni.mobileleleoffers.model.entity;

import bg.softuni.mobileleleoffers.model.enums.EngineTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;
    @Column
    private int mileage;
    @Column
    private double price;

    public Offer() {
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public Offer setEngine(EngineTypeEnum engine) {
        this.engine = engine;
        return this;
    }

    public long getId() {
        return id;
    }

    public Offer setId(long id) {
        this.id = id;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public Offer setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Offer setPrice(double price) {
        this.price = price;
        return this;
    }
}