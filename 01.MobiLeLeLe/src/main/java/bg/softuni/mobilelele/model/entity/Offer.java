package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.Engine;
import bg.softuni.mobilelele.model.enums.Transmission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    @Column(
            name = "image-url"
    )
    private String imageUrl;
    @Column
    private int mileage;
    @Column
    private double price;
    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column
    private Integer year;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @OneToOne
    private Model model;
    @OneToOne
    private User seller;

    public Offer() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return this.transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return this.year;
    }

    public Offer setYear(Integer year) {
        this.year = year;
        return this;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Model getModel() {
        return this.model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
