package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(
            name = "image_url"
    )
    private String imageUrl;
    @Column(
            name = "start_year"
    )
    private int startYear;
    @Column(
            name = "end_year"
    )
    private int endYear;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @OneToOne
    private Brand brand;

    public Model() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
