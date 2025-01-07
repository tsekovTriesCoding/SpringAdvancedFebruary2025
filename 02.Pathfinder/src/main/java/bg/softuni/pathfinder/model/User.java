package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(
            nullable = false,
            unique = true
    )
    private String username;
    @Column
    private Integer age;
    @Column
    private String password;
    @Column(
            name = "full_name"
    )
    private String fullName;
    @Column
    private String email;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    private Set<Role> roles;
    @Enumerated(EnumType.STRING)
    private Level level;

    public User() {
        this.roles = new HashSet<>();
    }
}