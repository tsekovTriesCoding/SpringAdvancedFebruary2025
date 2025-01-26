package bg.softuni.pathfinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(
            nullable = false
    )
    private boolean approved;
    @Column
    private Instant created;
    @Column(
            name = "text_content",
            columnDefinition = "TEXT"
    )
    private String content;
    @ManyToOne(
            optional = false
    )
    private User author;
    @ManyToOne(
            optional = false
    )
    private Route route;
}