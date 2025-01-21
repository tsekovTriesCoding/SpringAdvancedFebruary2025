package bg.softuni.mobileleleoffers.model.entity;

import bg.softuni.mobileleleoffers.model.enums.EngineTypeEnum;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")// mySQL specific have to use containers for the tests to pass correctly
    private String description;
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;
    @Column
    private int mileage;
    @Column
    private double price;
    @Column(nullable = false)
    private Instant created = Instant.now();

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

    public Instant getCreated() {
        return created;
    }

    public Offer setCreated(Instant created) {
        this.created = created;
        return this;
    }
}