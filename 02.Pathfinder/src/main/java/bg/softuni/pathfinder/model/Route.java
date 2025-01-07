package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            name = "gpx_coordinates",
            columnDefinition = "LONGTEXT"
    )
    private String gpxCoordinates;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(
            nullable = false,
            unique = true
    )
    private String name;
    @ManyToOne(
            optional = false
    )
    private User author;
    @Column
    private String videoUrl;
    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "route"
    )
    private Set<Comment> comments;
    @OneToMany(
            targetEntity = Picture.class,
            mappedBy = "route"
    )
    private Set<Picture> pictures;
    @ManyToMany
    private Set<Category> categories;

    public Route() {
        this.comments = new HashSet<>();
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }
}